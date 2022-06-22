<template>
    <nav class="navbar shadow bg-white rounded justify-content-between flex-nowrap flex-row fixed-top">
        <div class="container">
            <img src="../assets/logo.png" alt="Renting" class="logo navbar-brand float-left">
            <ul class="nav navbar-nav flex-row float-right">
                <li class="nav-item">
                    <div style="height:40px; width:40px; margin-top:3px;">
                        <router-link exact to="/profile-page-fishing-instructor" data-bs-toggle="tooltip" data-bs-placement="top" title="Profile page"><font-awesome-icon class="fa-2x" icon="fa-solid fa-user" /></router-link>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="servicesDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Services
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="servicesDropdownMenuLink">
                        <li><router-link exact to="/service-crud" class="dropdown-item">My services</router-link></li>
                        <li><router-link exact to="/reservation-history-privileged-user" class="dropdown-item">Reservation history</router-link></li>
                        <li><router-link exact to="/service-availability" class="dropdown-item">My services availability</router-link></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <router-link class="btn btn-outline-primary" exact to="/reports">Business reports</router-link>
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
