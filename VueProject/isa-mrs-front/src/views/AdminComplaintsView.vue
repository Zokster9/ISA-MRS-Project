<template>
    <div>
        <NavbarAdmin></NavbarAdmin>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Complaints </th>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center align-middle"> <b> Client </b></td>
                            <td class="text-center align-middle"> <b> Owner </b></td>
                            <td class="text-center align-middle"> <b> Service </b></td>
                            <td class="text-center align-middle"> <b> Complaint </b></td>
                            <td class="text-center align-middle"> <b> Response </b></td>
                            <td class="text-center align-middle"> <b> Send response </b></td>
                        </tr>
                        <AdminComplaintRow v-for="complaint in this.complaints" :complaint="complaint" :key="complaint.id"> </AdminComplaintRow>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import AdminComplaintRow from '@/components/AdminComplaintRow.vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import Vue from 'vue'
    import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: 'AdminComplaintsView',
        components: {
            NavbarAdmin,
            AdminComplaintRow
        },
        data(){
            return {
                complaints: []
            }
        },
        mounted () {
            if (window.sessionStorage.getItem('role') === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
                axios.get("http://localhost:8088/complaints/findAll",{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.complaints = response.data
                })
            }else {
                router.push("/");
            }
        }
    }
</script>