document.addEventListener("DOMContentLoaded", () => {
  inicializarPagina();
  

  
});

let productosDesdeAPI = [];


document.addEventListener("DOMContentLoaded", () => {
    const usuario = JSON.parse(localStorage.getItem("usuarioActivo") || "{}");
    const btnInventario = document.getElementById("btnInventario");

    // Validación por nombre (en mayúsculas)
    if (usuario.nombre !== "ADMIN") {
      btnInventario.style.display = "none"; // Oculta el botón si no es ADMIN
    } else {
      btnInventario.style.display = "inline-block"; // Muestra solo si es ADMIN
    }
  });


function inicializarPagina() {
  cargarFiltros();
  cargarCategoriasVisuales();
  document.getElementById("filtros-container").addEventListener("change", aplicarFiltros);
  cargarProductosDesdeBackend();

  configurarBotonVolver();
}

/* ──────── FILTROS ──────── */
function cargarFiltros() {
  const filtros = [
    crearDefinicionFiltro("Precio", "precio", [
      { value: "0-500", label: "$0 - $500" },
      { value: "500-1000", label: "$500 - $1000" },
      { value: "1000+", label: "Más de $1000" }
    ]),
    crearDefinicionFiltro("Descuento", "descuento", [
      { value: "10", label: "10%" },
      { value: "15", label: "15%" },
      { value: "20", label: "20%" },
      { value: "30", label: "30%" }
    ])
  ];

  const contenedor = document.getElementById("filtros-container");
  filtros.forEach(filtro => contenedor.appendChild(filtro));
}

function crearDefinicionFiltro(titulo, id, opciones) {
  const contenedor = document.createElement("div");
  contenedor.classList.add("filtro");

  const boton = document.createElement("button");
  boton.classList.add("toggle-btn");
  boton.textContent = `${titulo} ▼`;
  boton.onclick = () => toggleSection(id);

  const contenido = document.createElement("div");
  contenido.classList.add("contenido-filtro");
  contenido.id = id;

  opciones.forEach(({ value, label }) => {
    const labelEl = document.createElement("label");
    labelEl.innerHTML = `<input type="checkbox" name="${id}" value="${value}"> ${label}`;
    contenido.appendChild(labelEl);
    contenido.appendChild(document.createElement("br"));
  });

  contenedor.appendChild(boton);
  contenedor.appendChild(contenido);
  return contenedor;
}

/* ──────── APLICAR FILTROS ──────── */
function aplicarFiltros() {
  mostrarTodasLasSecciones();

  const productos = productosDesdeAPI;
  const filtrosPrecio = obtenerFiltrosSeleccionados("precio");
  const filtrosDescuento = obtenerFiltrosSeleccionados("descuento");

  const productosFiltrados = productos.filter(producto => {
    const cumplePrecio = filtrosPrecio.length === 0 || filtrosPrecio.some(rango => {
      if (rango === "1000+") return producto.precio >= 1000;
      const [min, max] = rango.split("-").map(Number);
      return producto.precio >= min && producto.precio <= max;
    });

    const cumpleDescuento = filtrosDescuento.length === 0 || filtrosDescuento.some(desc => {
      const descuentoCalculado = obtenerDescuento(producto);
      return descuentoCalculado >= parseInt(desc);
    });

    return cumplePrecio && cumpleDescuento;
  });

  mostrarProductosFiltrados(productosFiltrados);
}

function obtenerFiltrosSeleccionados(nombre) {
  return Array.from(document.querySelectorAll(`input[name="${nombre}"]:checked`))
    .map(input => input.value);
}

/* ──────── MOSTRAR PRODUCTOS FILTRADOS Y OFERTAS ──────── */
function mostrarProductosFiltrados(productos) {
  const contenedores = [
    "grid-invernadero",
    "grid-malla-sombra",
    "grid-mallas-decorativas",
    "grid-accesorios"
  ];
  contenedores.forEach(id => {
    const cont = document.getElementById(id);
    if (cont) cont.innerHTML = "";
  });

  const secciones = {
    "productos para invernadero": "grid-invernadero",
    "malla sombra": "grid-malla-sombra",
    "mallas decorativas": "grid-mallas-decorativas",
    "accesorios hidroponia": "grid-accesorios"
  };

  const ofertas = document.getElementById("ofertas-flash");
  if (ofertas) ofertas.innerHTML = "";

  productos.forEach(producto => {
    const categoriaKey = producto.categoria.trim().toLowerCase();
    const contenedorId = secciones[categoriaKey];
    const contenedor = document.getElementById(contenedorId);
    if (!contenedor) return;

    const tarjeta = crearTarjetaProducto(producto);
    contenedor.appendChild(tarjeta);

    if (producto.precioOriginal && producto.precioOriginal > producto.precio) {
      const ofertaCard = crearTarjetaProducto(producto, "producto-card oferta");
      const col = document.createElement("div");
      col.className = "oferta wrapper";
      col.appendChild(ofertaCard);
      ofertas?.appendChild(col);
    }
  });
}

/* ──────── CATEGORÍAS VISUALES ──────── */
function cargarCategoriasVisuales() {
  const categorias = [
    crearCategoria("INVERNADERO HTA", "Productos para invernadero", "productos-invernadero", "https://www.hta.mx/.cm4all/mediadb/HTA%20INICIO/CENITAL2.jpg"),
    crearCategoria("INVERNADEROS HTA", "Malla sombra", "malla-sombra", "https://www.hta-agrotextil.com/.cm4all/mediadb/MALLA%20SOMBRA.jpg"),
    crearCategoria("INVERNADEROS HTA", "Mallas decorativas", "mallas-decorativas", "https://www.hta-agrotextil.com/.cm4all/mediadb/RECIENTES/ESTRUCTURAL-CLARA-1024x683.jpg"),
    crearCategoria("INVERNADEROS HTA", "Accesorios Hidroponia", "accesorios-hidroponia", "https://cdn.pixabay.com/photo/2019/06/06/08/00/hydroponics-4255401_1280.jpg")
  ];

  const grid = document.getElementById("product-grid");
  grid.innerHTML = "";
  categorias.forEach(cat => grid.appendChild(cat));
}

function crearCategoria(categoria, titulo, destinoId, imagen) {
  const card = document.createElement("a");
  card.className = "card";
  card.href = "#";

  card.innerHTML = `
    <div class="card__background" style="background-image: url(${imagen})"></div>
    <div class="card__content">
      <p class="card__category">${categoria}</p>
      <h3 class="card__heading">${titulo}</h3>
    </div>
  `;

  card.addEventListener("click", e => {
    e.preventDefault();
    mostrarSoloSeccion(destinoId);
  });

  return card;
}

function mostrarSoloSeccion(idVisible) {
  const ids = ["productos-invernadero", "malla-sombra", "mallas-decorativas", "accesorios-hidroponia"];
  ids.forEach(id => {
    const sec = document.getElementById(id);
    if (sec) sec.style.display = id === idVisible ? "block" : "none";
  });

  document.getElementById("volverCatalogoContainer").style.display = "block";
  window.scrollTo({ top: 0, behavior: "instant" });
}

function configurarBotonVolver() {
  const btn = document.getElementById("btnVolverCatalogo");
  if (btn) {
    btn.addEventListener("click", mostrarTodasLasSecciones);
  }
}

function mostrarTodasLasSecciones() {
  const ids = ["productos-invernadero", "malla-sombra", "mallas-decorativas", "accesorios-hidroponia"];
  ids.forEach(id => {
    const sec = document.getElementById(id);
    if (sec) sec.style.display = "block";
  });

  document.getElementById("volverCatalogoContainer").style.display = "none";
  window.scrollTo({ top: 0, behavior: "smooth" });
}


/* ──────── CARGAR PRODUCTOS ──────── */

async function cargarProductosDesdeBackend() {
  try {
    const response = await fetch("http://localhost:8088/api/v1/products");
    if (!response.ok) throw new Error("Error al cargar productos");

    const productosBackend = await response.json();
    const productosAdaptados = productosBackend.map(p => ({
      nombre: p.productName,
      precio: p.price,
      precioOriginal: p.discount > 0 ? p.price / (1 - p.discount / 100) : p.price,
      descripcion: p.description,
      imagen: p.image,
      stock: p.stock,
      descuento: p.discount,
      categoria: p.category.name
    }));

    productosDesdeAPI = productosAdaptados;
    mostrarProductosFiltrados(productosDesdeAPI);

  } catch (error) {
    console.error("Error al cargar productos desde API", error);
    alert("No se pudieron cargar los productos desde el servidor.");
  }
}

/* ──────── TARJETAS ──────── */
function crearTarjetaProducto(producto, claseCard = "producto-card") {
  const { imagen, nombre, descripcion, precio, precioOriginal } = producto;

  const descuento = obtenerDescuento(producto);
  const descuentoHTML = descuento > 0
    ? `<span class="descuento">${descuento}% OFF</span>`
    : "";

  const card = document.createElement("div");
  card.className = claseCard;
  card.innerHTML = `
    <img src="${imagen}" alt="${nombre}" class="producto-img" />
    <h3 class="producto-nombre">${nombre}</h3>
    <p class="producto-descripcion">${descripcion || "Sin descripción disponible."}</p>
    <p class="producto-precio">
      <span class="precio-actual">$${precio.toFixed(2)}</span>
      ${precioOriginal && precioOriginal > precio
        ? `<span class="precio-original">$${precioOriginal.toFixed(2)}</span>`
        : ""}
      ${descuentoHTML}
    </p>
    <button class="btn-agregar-carrito">Agregar al carrito</button>
  `;

  card.querySelector(".btn-agregar-carrito").addEventListener("click", () => agregarAlCarrito(producto));
  return card;
}

/* ──────── UTILIDADES ──────── */
function obtenerDescuento(producto) {
  if (!producto.precioOriginal || producto.precioOriginal <= producto.precio) return 0;
  return Math.round(((producto.precioOriginal - producto.precio) / producto.precioOriginal) * 100);
}

function agregarAlCarrito(producto) {
  const { nombre, precio, imagen } = producto;
  const nuevoProducto = { nombre, precio, imagen, cantidad: 1 };

  const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  const existente = carrito.find(p => p.nombre === nombre);

  if (existente) {
    existente.cantidad += 1;
  } else {
    carrito.push(nuevoProducto);
  }

  localStorage.setItem("carrito", JSON.stringify(carrito));
  alert("Producto añadido al carrito ✅");
}

function toggleSection(id) {
  const section = document.getElementById(id);
  if (section) {
    section.style.display = section.style.display === "none" ? "block" : "none";
  }
}
