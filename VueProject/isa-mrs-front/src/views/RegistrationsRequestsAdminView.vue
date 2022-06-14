<template>
    <div>
        <NavbarAdmin></NavbarAdmin>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Registration Requests </th>
                    </thead>
                    <tbody>
                        <tr>
                            <td> <b> Name </b> </td>
                            <td> <b> Registration reasoning </b> </td>
                            <td colspan="2" class="text-center"> <b> Options </b> </td>
                        </tr>
                        <RegistrationRequestsRow v-for="user in unactivatedUsers" :user="user" :key="user.id"></RegistrationRequestsRow>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import RegistrationRequestsRow from '@/components/RegistrationRequestsRow.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: "RegistrationsRequestsAdmin",
        components: {
            RegistrationRequestsRow,
            NavbarAdmin,
        },
        data () {
            return {
                unactivatedUsers: [],
            }
        },
        mounted(){
            if (window.sessionStorage.getItem('role') === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
                axios.get("http://localhost:8088/users/inactive",{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) => {
                    this.unactivatedUsers = response.data;
                })
            }
            else {
                router.push("/");
            }
        },
    }
</script>