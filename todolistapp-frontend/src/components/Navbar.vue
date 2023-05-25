<template>
  <nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">ToDoList-App</a>
      </div>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <div class="navbar-nav">
          <router-link to="/">Home</router-link>
          <router-link to="/about">About</router-link>
          <!-- <a class="nav-link active" aria-current="page" href="#">Home</a>
           <a class="nav-link" href="#">About</a>-->
        </div>
      </div>
  </nav>
</template>
<script>
export default {
  name: 'NavbarComponent'
}
</script>
