<template>
    <div class="flex">
        <NavbarAdmin></NavbarAdmin>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Users </th>
                        
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center align-items"> <b> Name and surname </b></td>
                            <td class="text-center align-items"> <b> Email </b></td>
                            <td class="text-center align-items"> <b> User type </b></td>
                            <td class="text-center align-items"> <b> DELETE </b></td>
                        </tr>
                        <AdminUserDataRow v-for="user in this.visibleUsers"
                        :user="user"
                        :key="user.id"> 
                        </AdminUserDataRow>
                        <tr>
                            <td colspan="3"></td>
                            <td>
                                <PaginationComponent :elements="this.users" v-on:page:update="updatePage" :currentPage="this.currentPage" :pageSize="this.pageSize"></PaginationComponent>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import AdminUserDataRow from '@/components/AdminUserDataRow.vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import Vue from 'vue'
    import router from '@/router'
    import PaginationComponent from '@/components/PaginationComponent.vue'

    Vue.use(VueAxios, axios)

    export default {
        name: 'AdminUsersView',
        components: {
            NavbarAdmin,
            AdminUserDataRow,
            PaginationComponent
        },
        data(){
            return {
                users: [],
                currentPage: 0,
                pageSize: 3,
                visibleUsers: []
            }
        },
        mounted () {
            if (window.sessionStorage.getItem('role') === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
                axios.get("http://localhost:8088/users/findAllUsers",{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.users = response.data
                    this.updateVisibleUsers();
                })
            }
            else {
                router.push("/");
            }
        },
        methods: {
            updateVisibleUsers(){
                //1. param: pocetni index, 2. param: index do kojeg prikazujemo
                this.visibleUsers = this.users.slice(this.currentPage * this.pageSize, (this.currentPage * this.pageSize) + this.pageSize);
                if (this.visibleUsers.length == 0 && this.currentPage > 0){
                    this.updatePage(this.currentPage - 1);
                }
            },
            updatePage(pageNumber){
                this.currentPage = pageNumber;
                this.updateVisibleUsers();
            },
        },
    }
</script>