<template>
    <nav class="nav nav-tabs flex-column">
        <router-link exact to="/profile-page-admin" tag="button" class="nav-link text-start">Personal info</router-link>
        <router-link exact to="/registration-requests" tag="button" class="nav-link text-start">Registration requests</router-link>
        <router-link exact to="/account-termination-requests" tag="button" class="nav-link text-start">Account termination requests</router-link>
        <router-link :class="{ disabledNavbarOption: !isMainAdminLogged }" exact to="/admin-sign-up" tag="button" class="nav-link text-start">Add administrators</router-link>
        <router-link exact to="/" tag="button" class="nav-link text-start">System entities</router-link>
        <router-link exact to="/" tag="button" class="nav-link text-start">Income information</router-link>
        <router-link exact to="/" tag="button" class="nav-link text-start">Business reports</router-link>
    </nav>
</template>
<script>
    export default({
        methods:{
            isMainAdminLogged(){
                if (window.localStorage.getItem("role") === "ROLE_mainAdmin"){
                    return true;
                }
                return false;
            }
        }
    })
</script>
