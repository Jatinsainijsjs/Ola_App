<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Customer</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Register</h2>
<form id="registerForm">
    <input type="text" id="username" placeholder="Username" required><br>
    <input type="password" id="password" placeholder="Password" required><br>
    <input type="text" id="email" placeholder="Email" required><br>
    <button type="submit">Register</button>
</form>
<div id="registerMessage"></div>

<script>
    const registerForm = document.getElementById("registerForm");
    registerForm.addEventListener("submit", async (e) => {
      e.preventDefault();

      const data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        email: document.getElementById("email").value,
      };

      const response = await fetch("http://localhost:8080/customer/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
      });

      if (response.ok) {
        document.getElementById("registerMessage").innerText = "Registered successfully!";
      } else {
        document.getElementById("registerMessage").innerText = "Registration failed.";
      }
    });
</script>
</body>
</html>
