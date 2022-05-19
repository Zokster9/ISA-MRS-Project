<template>
    <nav class="nav nav-tabs flex-column" style="width:200px">
        <router-link exact to="/profile-page-admin" tag="button" class="nav-link text-start">Personal info</router-link>
        <router-link exact to="/registration-requests" tag="button" class="nav-link text-start">Registration requests</router-link>
        <router-link exact to="/account-termination-requests" tag="button" class="nav-link text-start">Account termination requests</router-link>
        <router-link :class="{ disabledNavbarOption: admin }" exact to="/admin-sign-up" tag="button" class="nav-link text-start">Add administrators</router-link>
        <router-link exact to="/admin-services-view" tag="button" class="nav-link text-start">System entities</router-link>
        <router-link exact to="/admin-users-view" tag="button" class="nav-link text-start"> System users </router-link>
        <router-link exact to="/admin-complaints" tag="button" class="nav-link text-start"> Complaints </router-link>
        <router-link exact to="#" tag="button" class="nav-link text-start"> Revisions </router-link>
        <router-link exact to="#" tag="button" class="nav-link text-start"> Reports </router-link>
        <router-link exact to="/" tag="button" class="nav-link text-start">Income information</router-link>
        <router-link exact to="/" tag="button" class="nav-link text-start">Business reports</router-link>
    </nav>
</template>
<script>
    export default({
        data(){
            return{
                admin: ""
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
