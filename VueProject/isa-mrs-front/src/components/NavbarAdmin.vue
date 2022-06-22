<template>
    <nav class="navbar shadow bg-white rounded justify-content-between flex-nowrap flex-row fixed-top">
        <div class="container">
            <img src="../assets/logo.png" alt="Renting" class="logo navbar-brand float-left">
            <ul class="nav navbar-nav flex-row float-right">
                <li class="nav-item">
                    <div style="height:40px; width:40px; margin-top:3px;">
                        <router-link exact to="/profile-page-admin" data-bs-toggle="tooltip" data-bs-placement="top" title="Profile page"><font-awesome-icon class="fa-2x" icon="fa-solid fa-user" /></router-link>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="servicesDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Services
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="servicesDropdownMenuLink">
                        <li><router-link exact to="/admin-services-view" class="dropdown-item">System entities</router-link></li>
                        <li><router-link exact to="/admin-complaints" class="dropdown-item">Complaints</router-link></li>
                        <li><router-link exact to="/admin-revisions" class="dropdown-item">Revisions</router-link></li>
                        <li><router-link exact to="/admin-reports" class="dropdown-item">Reports</router-link></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="usersDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Users
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="usersDropdownMenuLink">
                        <li><router-link exact to="/admin-users-view" class="dropdown-item">System users</router-link></li>
                        <li><router-link exact to="/registration-requests" class="dropdown-item">Registration requests</router-link></li>
                        <li><router-link exact to="/account-termination-requests" class="dropdown-item">Account termination requests</router-link></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="reservationHistoryDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Economy
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="reservationHistoryDropdownMenuLink">
                        <li><router-link exact to="/admin-business-income" class="dropdown-item">Income change and business reports</router-link></li>
                        <li><router-link exact to="/admin-loyalty-program" class="dropdown-item">Loyalty program</router-link></li>                    </ul>
                </li>
                <li class="nav-item">
                    <router-link :class="{ disabledNavbarOption: admin }" class="btn btn-outline-primary" exact to="/admin-sign-up">Add administrator</router-link>
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

    export default({
        data(){
            return{
                admin: ""
            }
        },
        methods: {
            logout() {
            window.sessionStorage.removeItem('accessToken');
            window.sessionStorage.removeItem('role');
            router.push('/');
        }
        },
        mounted(){
            if (window.sessionStorage.getItem("role") === "ROLE_admin"){
                this.admin = true;
            }
            else{
                this.admin = false;
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
