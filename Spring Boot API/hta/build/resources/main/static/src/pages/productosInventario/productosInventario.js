const API_BASE = "http://localhost:8080/api/v1/products";

let editando = false;
let codigoProductoEditando = null;

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("formAgregarProducto");
  const grid = document.getElementById("product-grid");
  const precioInput = document.getElementById("precio");
  const descuentoInput = document.getElementById("descuento");
  const precioFinalInput = document.getElementById("precioFinal");
  const imagenInput = document.getElementById("imagen");
  const previewImage = document.getElementById("previewImage");
  const productoModal = new bootstrap.Modal(document.getElementById('productoModal'));
  const buscador = document.getElementById("buscador");
  const filtroCategoria = document.getElementById("filtroCategoria");
  const bajoStockCheckbox = document.getElementById("bajoStock");
  const descuentoCheckbox = document.getElementById("descuentos");

  // Calcular precio final con descuento
  function calcularPrecioFinal() {
    const precio = parseFloat(precioInput.value);
    const descuento = parseFloat(descuentoInput.value);
    if (!isNaN(precio)) {
      if (!isNaN(descuento) && descuento >= 0 && descuento <= 100) {
        const precioFinal = precio * (1 - descuento / 100);
        precioFinalInput.value = `$${precioFinal.toFixed(2)}`;
      } else {
        precioFinalInput.value = `$${precio.toFixed(2)}`;
      }
    } else {
      precioFinalInput.value = "";
    }
  }

  // Preview imagen
  imagenInput.addEventListener("input", () => {
    const url = imagenInput.value.trim();
    if (url) {
      previewImage.src = url;
      previewImage.classList.remove("d-none");
    } else {
      previewImage.src = "";
      previewImage.classList.add("d-none");
    }
  });

  precioInput.addEventListener("input", calcularPrecioFinal);
  descuentoInput.addEventListener("input", calcularPrecioFinal);

  // Crear tarjeta producto con botones editar y eliminar
  function crearTarjeta(producto) {
    const tarjeta = document.createElement("div");
    tarjeta.className = "product-card card p-3 mb-3";
    if (producto.stock < 50) tarjeta.classList.add("low-stock");

    const precio = parseFloat(producto.precio);
    const precioOriginal = parseFloat(producto.precioOriginal);

    tarjeta.innerHTML = `
      <img src="${producto.imagen}" alt="${producto.nombre}" class="img-fluid mb-2" style="max-height: 150px; object-fit: contain;">
      <p><strong>Código:</strong> ${producto.codigo}</p>
      <p><strong>Nombre:</strong> ${producto.nombre}</p>
      <p><strong>Descripción:</strong> ${producto.descripcion}</p>
      <p>
        <strong>Precio:</strong> <span class="text-success fw-bold">$${!isNaN(precio) ? precio.toFixed(2) : 'N/A'}</span>
        ${!isNaN(precioOriginal) && precioOriginal > precio
          ? `<br><small class="text-muted text-decoration-line-through">$${precioOriginal.toFixed(2)}</small>`
          : ""
        }
      </p>
      <p class="stock-info ${producto.stock < 50 ? 'text-danger' : ''}">Disponibles: ${producto.stock}</p>
      <p><strong>Categoría:</strong> ${producto.categoria || '-'}</p>
      <button class="btn btn-outline-primary btn-sm me-2 editar-btn">Editar</button>
      <br></br>
      <button class="btn btn-outline-danger btn-sm eliminar-btn">Eliminar</button>
    `;

    tarjeta.querySelector(".eliminar-btn").addEventListener("click", () => {
      if (confirm("¿Estás seguro de que deseas eliminar este producto?")) {
        eliminarProducto(producto.codigo);
      }
    });

    tarjeta.querySelector(".editar-btn").addEventListener("click", () => {
      abrirEditarProducto(producto);
    });

    return tarjeta;
  }

  // Filtrar productos por búsqueda, categoría, stock bajo y descuento
  function filtrarProductos(productos) {
    const terminoBusqueda = buscador.value.toLowerCase();
    const categoriaSeleccionada = filtroCategoria.value;
    const filtroStockBajo = bajoStockCheckbox.checked;
    const filtroDescuento = descuentoCheckbox.checked;

    return productos.filter(producto => {
      const cumpleBusqueda =
        producto.nombre.toLowerCase().includes(terminoBusqueda) ||
        producto.codigo.toLowerCase().includes(terminoBusqueda);

      const cumpleCategoria = categoriaSeleccionada === "" || producto.categoria === categoriaSeleccionada;

      const cumpleStockBajo = filtroStockBajo ? producto.stock < 50 : true;

      const tieneDescuento = producto.precioOriginal && producto.precioOriginal > producto.precio;
      const cumpleDescuento = filtroDescuento ? tieneDescuento : true;

      return cumpleBusqueda && cumpleCategoria && cumpleStockBajo && cumpleDescuento;
    });
  }

  // Cargar productos desde backend y renderizar
  async function cargarProductosDesdeBackend() {
    try {
      const response = await fetch(`${API_BASE}`);
      if (!response.ok) throw new Error("Error al cargar productos");
      const productosBackend = await response.json();

      // Adaptar estructura para UI
      let productos = productosBackend.map(producto => ({
        codigo: producto.id,
        nombre: producto.productName,
        descripcion: producto.description,
        precio: producto.price,
        precioOriginal: producto.discount > 0 ? producto.price / (1 - producto.discount / 100) : producto.price,
        descuento: producto.discount,
        stock: producto.stock,
        imagen: producto.image,
        categoria: producto.category ? producto.category.id : '-'
      }));

      productos = filtrarProductos(productos);

      grid.innerHTML = "";
      productos.forEach(producto => {
        grid.appendChild(crearTarjeta(producto));
      });

    } catch (error) {
      console.error(error);
      alert("No se pudieron cargar los productos");
    }
  }

  // Eliminar producto por ID (codigo)
  async function eliminarProducto(codigo) {
    try {
      const response = await fetch(`${API_BASE}/${codigo}`, {
        method: "DELETE"
      });
      if (!response.ok) throw new Error("Error al eliminar producto");
      await cargarProductosDesdeBackend();
    } catch (error) {
      alert(error.message);
    }
  }

  // Abrir modal para editar producto
  function abrirEditarProducto(producto) {
    editando = true;
    codigoProductoEditando = producto.codigo;

    form.codigo.value = producto.codigo; // solo para mostrar, lo deshabilitamos
    form.nombre.value = producto.nombre;
    form.descripcion.value = producto.descripcion;
    form.precio.value = producto.precio;
    form.descuento.value = producto.descuento;
    calcularPrecioFinal();
    form.stock.value = producto.stock;
    form.imagen.value = producto.imagen;
    form.categoria.value = producto.categoria || '';

    previewImage.src = producto.imagen;
    previewImage.classList.remove("d-none");

    form.codigo.disabled = true;

    productoModal.show();
  }

  // Reset formulario para agregar nuevo producto
  function resetForm() {
    form.reset();
    precioFinalInput.value = "";
    previewImage.src = "";
    previewImage.classList.add("d-none");
    form.codigo.disabled = false;
    editando = false;
    codigoProductoEditando = null;

    // Limpiar errores visuales
    document.getElementById("errorNombre").classList.add("d-none");
    document.getElementById("errorCodigo").classList.add("d-none");
    form.nombre.classList.remove("is-invalid");
    form.codigo.classList.remove("is-invalid");
  }

  // Enviar formulario para crear o actualizar producto
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const codigo = form.codigo.value.trim(); // solo para edición
    const nombre = form.nombre.value.trim();
    const descripcion = form.descripcion.value.trim();
    const precioOriginal = parseFloat(form.precio.value);
    const descuento = parseFloat(form.descuento.value);
    const stock = parseInt(form.stock.value);
    const imagen = form.imagen.value.trim();
    const categoriaId = form.categoria.value;

    // Validaciones (igual que antes)
    const errorNombre = document.getElementById("errorNombre");
    const errorCodigo = document.getElementById("errorCodigo");
    const errorDescripcion = document.getElementById("errorDescripcion");

    const nombreValido = /^[a-zA-Z0-9\sáéíóúÁÉÍÓÚñÑ-]+$/.test(nombre);
    let hayErrores = false;

    if (!nombreValido) {
      errorNombre.classList.remove("d-none");
      form.nombre.classList.add("is-invalid");
      hayErrores = true;
    } else {
      errorNombre.classList.add("d-none");
      form.nombre.classList.remove("is-invalid");
    }

    if (!codigo && editando) {
      alert("Código del producto inválido para edición");
      return;
    }

    if (!descripcion || descripcion.length > 200) {
      errorDescripcion.classList.remove("d-none");
      form.descripcion.classList.add("is-invalid");
      hayErrores = true;
    } else {
      errorDescripcion.classList.add("d-none");
      form.descripcion.classList.remove("is-invalid");
    }

    if (!categoriaId) {
      alert("Selecciona una categoría válida");
      hayErrores = true;
    }

    if (hayErrores) return;

    // Calculamos precio final
    const precio = !isNaN(descuento) && descuento >= 0 && descuento <= 100
      ? precioOriginal * (1 - descuento / 100)
      : precioOriginal;

    // Construimos el producto para backend
    const nuevoProducto = {
        productName: nombre,
  price: precio,
  description: descripcion,
  stock: stock,
  image: imagen,
  discount: isNaN(descuento) ? 0 : descuento,
  categoryId: parseInt(categoriaId)
    };

    try {
      if (editando) {
        const response = await fetch(`${API_BASE}/${codigoProductoEditando}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(nuevoProducto)
        });

        if (!response.ok) throw new Error("Error al actualizar producto");
      } else {
        const response = await fetch(`${API_BASE}`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(nuevoProducto)
        });

        if (!response.ok) throw new Error("Error al guardar producto");
      }

      await cargarProductosDesdeBackend();

      resetForm();
      productoModal.hide();

    } catch (error) {
      alert(error.message);
    }
  });

  // Enlazar filtros a la recarga
  buscador.addEventListener("input", cargarProductosDesdeBackend);
  filtroCategoria.addEventListener("change", cargarProductosDesdeBackend);
  bajoStockCheckbox.addEventListener("change", cargarProductosDesdeBackend);
  descuentoCheckbox.addEventListener("change", cargarProductosDesdeBackend);

  // Inicializar la carga
  cargarProductosDesdeBackend();

  // Contador para descripción
  form.descripcion.addEventListener("input", () => {
    const contador = document.getElementById("descripcionContador");
    contador.textContent = `${form.descripcion.value.length} / 200 caracteres`;
  });

});