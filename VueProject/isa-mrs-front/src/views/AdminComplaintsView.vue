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
                        <th colspan="100%" class="text-center h2"> Complaints </th>
                        
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center align-items"> <b> Client </b></td>
                            <td class="text-center align-items"> <b> Privileged user </b></td>
                            <td class="text-center align-items"> <b> Service </b></td>
                            <td class="text-center align-items"> <b> Complaint </b></td>
                            <td class="text-center align-items"> <b> Response </b></td>
                            <td class="text-center align-items"> <b> Send response </b></td>
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
            axios.get("http://localhost:8088/complaints/findAll",{
                headers:{
                    Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                }
            }).then((response) =>{
                this.complaints = response.data
            })
        }
    }
</script>