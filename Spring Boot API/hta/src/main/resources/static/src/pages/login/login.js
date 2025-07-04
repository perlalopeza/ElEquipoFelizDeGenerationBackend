import { insertFooter } from "../../modules/footer/footer.js";

insertFooter(document.getElementById("footer"));
const form = document.getElementById('login-form');
const emailInput = document.getElementById('username');
const passInput = document.getElementById('password');
const emailErrorDiv = document.getElementById('usernameError');
const passErrorDiv = document.getElementById('passwordError');

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  clearValidation();

  const email = emailInput.value.trim();
  const password = passInput.value;

  let valid = true;

  // Validación email
  if (!email) {
    showError(emailInput, emailErrorDiv, 'El correo electrónico es obligatorio.');
    valid = false;
  } else if (!validateEmail(email)) {
    showError(emailInput, emailErrorDiv, 'Formato de correo inválido.');
    valid = false;
  }

  // Validación contraseña
  if (!password) {
    showError(passInput, passErrorDiv, 'La contraseña es obligatoria.');
    valid = false;
  }

  if (!valid) return;

  try {
    const response = await fetch("http://localhost:8088/api/v1/users/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ email, password }) // Enviamos al backend
    });

    if (!response.ok) {
      const errorText = await response.text();
      showError(passInput, passErrorDiv, errorText || "Error de autenticación.");
      return;
    }

    const user = await response.json();

    localStorage.setItem('usuarioActivo', JSON.stringify({
      email: user.email,
      nombre: user.name,
      apellido: user.lastName,
      telefono: user.phone
    }));

    alert('¡Inicio de sesión exitoso!');
        setTimeout(() => {
            window.location.href = "../../../index.html";
        }, 1000);

    } catch (error) {
        console.error("Error en el login:", error);
        showError(passInput, passErrorDiv, "Error de servidor.");
    }
});



function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function showError(inputElem, errorDiv, message) {
    inputElem.classList.add('is-invalid');
    errorDiv.textContent = message;
}

function clearValidation() {
    [emailInput, passInput].forEach(input => input.classList.remove('is-invalid'));
    emailErrorDiv.textContent = '';
    passErrorDiv.textContent = '';
}

/*
async function hashPassword(password) {
    const encoder = new TextEncoder();
    const data = encoder.encode(password);
    const hashBuffer = await crypto.subtle.digest('SHA-256', data);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    return hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
}*/
