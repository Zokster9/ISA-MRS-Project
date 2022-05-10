<template>
    <nav class="navbar shadow bg-white rounded justify-content-between flex-nowrap flex-row fixed-top">
        <div class="container">
            <router-link exact to="/home-page-client"><img src="../assets/logo.png" alt="Renting" class="logo navbar-brand float-left"></router-link>
            <ul class="nav navbar-nav flex-row float-right">
                <li class="nav-item">
                    <router-link exact to="/profile-page-client" class="nav-link text-start">Personal info</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/" class="nav-link text-start">All retreats</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/" class="nav-link text-start">All ships</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/" class="nav-link text-start">All fishing instructors</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/" class="nav-link text-start">All retreat reservations</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/" class="nav-link text-start">All ship reservations</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/" class="nav-link text-start">All adventure reservations</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/" class="nav-link text-start">All upcoming reservations</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/penalty-points" class="nav-link text-start">My penalty points</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/" class="nav-link text-start">All subscriptions</router-link>
                </li>
                <li class="nav-item">
                    <router-link exact to="/" class="nav-link text-start">Write a complaint</router-link>
                </li>
            </ul>
        </div>
    </nav>
</template>