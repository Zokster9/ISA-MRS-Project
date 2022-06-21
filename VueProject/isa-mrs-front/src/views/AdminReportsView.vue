<template>
    <div>
        <NavbarAdmin></NavbarAdmin>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Unanswered reports </th>                       
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center align-middle"> <b> Client </b></td>
                            <td class="text-center align-middle"> <b> Owner </b></td>
                            <td class="text-center align-middle"> <b> Service </b></td>
                            <td class="text-center align-middle"> <b> Report </b></td>
                            <td class="text-center align-middle"> <b> Accept report </b></td>
                            <td class="text-center align-middle"> <b> Decline report </b></td>
                        </tr>
                        <AdminReportRow @removeReport="removeReport" v-for="report in this.reports" :report="report" :key="report.id"> </AdminReportRow>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import AdminReportRow from '@/components/AdminReportRow.vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import Vue from 'vue'
    import router from '@/router'
    Vue.use(VueAxios, axios)
    export default {
        name: 'AdminReportsView',
        components: {
            NavbarAdmin,
            AdminReportRow
        },
        data(){
            return {
                reports: []
            }
        },
        methods: {
            removeReport(id) {
                this.reports = this.reports.filter(report => id != report.id)
            }
        },
        mounted () {
            if (window.sessionStorage.getItem('role') === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
                axios.get("https://isa-project-tim3.herokuapp.com/reports/findAll",{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.reports = response.data
                })
            }else {
                router.push("/");
            }
        }
    }
</script>