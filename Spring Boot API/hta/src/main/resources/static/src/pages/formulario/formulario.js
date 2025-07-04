import { insertFooter } from "../../modules/footer/footer.js";

insertFooter(document.getElementById("footer"));

// Función para hashear la contraseña (debe ir antes de su uso)
async function hashPassword(password) {
  const encoder = new TextEncoder();
  const data = encoder.encode(password);
  const hashBuffer = await crypto.subtle.digest('SHA-256', data);
  const hashArray = Array.from(new Uint8Array(hashBuffer));
  const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
  return hashHex;
}

//Validaciones Formulario//

document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('registerForm');

  form.addEventListener('submit', async function (e) { //se agrega async 
    e.preventDefault();

    const name = document.getElementById('name').value.trim();
    const lastName = document.getElementById('lastName').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const terminos = document.getElementById('terminos').checked;

    const fieldIds = ['name', 'lastName', 'phone', 'email', 'password', 'confirmPassword'];
    clearFieldErrors(fieldIds);

    let isValid = true;
    //Validacion nombre
    if (!name) {
      showFieldError('name', 'El nombre es requerido');
      isValid = false;
    } else if (name.length < 3 || !name.replace(/\s/g, '')) { //elimina todos los espacios en blanco (\s) del nombre
      showFieldError('name', 'El nombre debe tener al menos 3 caracteres y no solo espacios');
      isValid = false;
    }
    //Validacion apellidos
    if (!lastName) {
      showFieldError('lastName', 'Los apellidos son requeridos');
      isValid = false;
    } else if (lastName.length < 3 || !lastName.replace(/\s/g, '')) { //requistos 
      showFieldError('lastName', 'El apellido debe tener al menos 3 caracteres y no solo espacios');
      isValid = false;
    }
    //Validación número de télefono
    const phoneDigits = phone.replace(/\D/g, ''); //borra espacios
    if (!phone) {
      showFieldError('phone', 'El número de teléfono es requerido');
      isValid = false;
    } else if (phoneDigits.length !== 10) {
      showFieldError('phone', 'El número debe tener exactamente 10 dígitos');
      isValid = false;
    }
    //Validacion correo electrónico
    if (!email) {
      showFieldError('email', 'El correo electrónico es requerido');
      isValid = false;
    } else if (!/^\S+@\S+\.\S+$/.test(email)) {//requisitos 
      showFieldError('email', 'El correo electrónico no es válido');
      isValid = false;
    }
    //Validación contraseña
    const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[#$%&.-_]).{8,}$/; //requisitos
    if (!password) {
      showFieldError('password', 'La contraseña es requerida');
      isValid = false;
    } else if (!passwordRegex.test(password)) { // comprueba si la contraseña cumple con los requisitos
      showFieldError('password', 'La contraseña no cumple con los requisitos');
      isValid = false;
    }
    //Validacion confirmar contraseña
    if (!confirmPassword) {
      showFieldError('confirmPassword', 'Debes confirmar la contraseña');
      isValid = false;
    } else if (password !== confirmPassword) {
      showFieldError('confirmPassword', 'Las contraseñas no coinciden');
      isValid = false;
    }

    //Validacion si se aceptan términos
    if (!terminos) {
      const alertaTerminos = document.getElementById('alertaTerminos');
      alertaTerminos.classList.remove('d-none');
      isValid = false;
      setTimeout(() => { //Elimna la alerta despues de 3 segundos
        alertaTerminos.classList.add('d-none');
      }, 3000);
    }

    if (isValid) {
      const hashedPassword = await hashPassword(password);
      const userData = {
        nombre: name,
        apellido: lastName,
        telefono: phone,
        email: email,
        password: hashedPassword // Aquí guarda la contraseña hasheada
      };

      const usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];
      const usuarioExistente = usuarios.find(u => u.email === email);

      if (usuarioExistente) { // Error si ese correo ya está registrado
        showFieldError('email', 'Este correo ya está registrado.');
        isValid = false;
        return;
      }

      // Guardar en localStorage
      usuarios.push(userData);
      localStorage.setItem("usuarios", JSON.stringify(usuarios));

      localStorage.setItem('usuarioActivo', JSON.stringify({
        email: email,
        nombre: name,
        apellido: lastName,
        telefono: phone
      }));
      window.dispatchEvent(new Event('storage')); // Notifica a otras pestañas

      // Mostrar mensaje y limpiar formulario
      console.log("Usuario registrado (JSON):", JSON.stringify(userData, null, 2));
      showSuccess('¡Usuario registrado correctamente!');
      this.reset();

      // Redirigir a la página de perfil después del registro exitoso
      setTimeout(() => {
        window.location.href = "../../../src/pages/perfilDeUsuario/perfilDeUsuario.html";
      }, 1000); // Redirige después de 1 segundo para mostrar el mensaje
    }
  });
});


// Funciones de erores /limpiar campos
function clearFieldErrors(fieldIds) {
  fieldIds.forEach(id => {
    const field = document.getElementById(id);
    const errorElement = field.nextElementSibling;
    if (errorElement && errorElement.classList.contains('text-danger')) {
      errorElement.textContent = '';
    }
  });
}
function showFieldError(fieldId, message) {
  const field = document.getElementById(fieldId);
  let errorDiv = field.nextElementSibling;

  // Div eroror: Mostrar un mensaje de error justo debajo del campo del formulario donde ocurrió la validación fallida.
  if (!errorDiv || !errorDiv.classList.contains('text-danger')) {
    errorDiv = document.createElement('div');
    errorDiv.className = 'text-danger mt-1';
    field.parentNode.insertBefore(errorDiv, field.nextSibling);
  }

  errorDiv.textContent = message;
}

// muestra alertas de bootstrap de error
function showErrors(errors) {
  const alertContainer = document.getElementById('alertContainer');
  alertContainer.innerHTML = ''; // limpiar anteriores
  errors.forEach(error => {
    const alert = document.createElement('div');
    alert.className = 'alert alert-danger alert-dismissible fade show';
    alert.innerHTML = `
            ${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
    alertContainer.appendChild(alert);
  });
}

// Mensaje de usuario registrado con exito 
function showSuccess(message) {
  const successDiv = document.getElementById('successMessage');
  successDiv.textContent = message;
  successDiv.classList.remove('d-none');

  // Ocultar después de 3 segundos mensaje de registro exitoso
  setTimeout(() => {
    successDiv.classList.add('d-none');
  }, 3000);
}

//limpiar el contenido HTML del contenedor de alertas.
function clearAlerts() {
  const alertContainer = document.getElementById('alertContainer');
  alertContainer.innerHTML = '';
}



