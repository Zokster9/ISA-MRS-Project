<template>
    <nav class="navbar shadow bg-white rounded justify-content-between flex-nowrap flex-row fixed-top">
        <div class="container">
            <router-link exact to="/main-screen"><img src="../assets/logo.png" alt="Renting" class="logo navbar-brand float-left"></router-link>
            <ul class="nav navbar-nav flex-row float-right">
                <li class="nav-item">
                    <router-link class="nav-link pr-3" exact to="/sign-in">Sign in</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="btn btn-outline-primary" exact to="/sign-up">Sign up</router-link>
                </li>
            </ul>
        </div>
    </nav>
</template>