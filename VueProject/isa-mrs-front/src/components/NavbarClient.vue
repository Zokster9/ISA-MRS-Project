<template>
    <nav class="navbar shadow bg-white rounded justify-content-between flex-nowrap flex-row fixed-top">
        <div class="container">
            <router-link exact to="/home-page-client"><img src="../assets/logo.png" alt="Renting" class="logo navbar-brand float-left"></router-link>
            <ul class="nav navbar-nav flex-row float-right">
                <li class="nav-item">
                    <div style="height:40px; width:40px; margin-top:3px;">
                        <router-link exact to="/profile-page-client" data-bs-toggle="tooltip" data-bs-placement="top" title="Profile page"><font-awesome-icon class="fa-2x" icon="fa-solid fa-user" /></router-link>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="servicesDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Services
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="servicesDropdownMenuLink">
                        <li><router-link exact to="/retreats" class="dropdown-item">All retreats</router-link></li>
                        <li><router-link exact to="/ships" class="dropdown-item">All ships</router-link></li>
                        <li><router-link exact to="/fishing-instructors" class="dropdown-item">All fishing instructors</router-link></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="reservationsDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reservations
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="reservationsDropdownMenuLink">
                        <li><router-link exact to="/make-a-reservation" class="dropdown-item">Make a reservation</router-link></li>
                        <li><router-link exact to="/pending-reservations" class="dropdown-item">Upcoming reservations</router-link></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="reservationHistoryDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reservation History
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="reservationHistoryDropdownMenuLink">
                        <li><router-link exact to="/client-retreat-reservation-history" class="dropdown-item">All retreat reservations</router-link></li>
                        <li><router-link exact to="/client-ship-reservation-history" class="dropdown-item">All ship reservations</router-link></li>
                        <li><router-link exact to="/client-adventure-reservation-history" class="dropdown-item">All adventure reservations</router-link></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="personalDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Personal Info
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="personalDropdownMenuLink">
                        <li><router-link exact to="/penalty-points" class="dropdown-item">My penalty points</router-link></li>
                        <li><router-link exact to="/client-subscriptions" class="dropdown-item">My subscriptions</router-link></li>
                        <li><router-link exact to="/write-a-complaint" class="dropdown-item">Write a complaint</router-link></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <button @click="logout" class="btn nav-link pr-3" data-bs-toggle="tooltip" data-bs-placement="top" title="Sign out"><font-awesome-icon class="fa-lg" icon="fa-solid fa-right-from-bracket" /></button>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
import router from '@/router';
import { defineComponent } from '@vue/composition-api'

export default defineComponent({
    methods: {
        logout() {
            window.sessionStorage.removeItem('accessToken');
            window.sessionStorage.removeItem('role');
            router.push('/');
        }
    }
})
</script>

<style scoped>

.btn:focus {
    outline: none;
    box-shadow: none;
}

</style>