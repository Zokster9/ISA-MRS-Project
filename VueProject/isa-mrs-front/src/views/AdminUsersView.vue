<template>
    <div class="d-flex flex-row">
        <div>
            <NavbarAdmin></NavbarAdmin>
        </div>
        <div class="row justify-content-center mx-auto">
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
                        <AdminUserDataRow v-for="user in this.users" :user="user" :key="user.id"> </AdminUserDataRow>
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

    Vue.use(VueAxios, axios)

    export default {
        name: 'AdminUsersView',
        components: {
            NavbarAdmin,
            AdminUserDataRow
        },
        data(){
            return {
                users: []
            }
        },
        mounted () {
            axios.get("http://localhost:8088/users/findAllUsers",{
                headers:{
                    Authorization: "Bearer " + window.localStorage.getItem("accessToken")
                }
            }).then((response) =>{
                this.users = response.data
            })
        }
    }
</script>