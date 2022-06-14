<template>
    <div>
        <NavbarAdmin></NavbarAdmin>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Revisions </th>
                        
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center align-middle"> <b> Client </b></td>
                            <td class="text-center align-middle"> <b> Owner </b></td>
                            <td class="text-center align-middle"> <b> Owner rating </b></td>
                            <td class="text-center align-middle"> <b> Service </b></td>
                            <td class="text-center align-middle"> <b> Service rating </b></td>
                            <td class="text-center align-middle"> <b> Revision </b></td>
                            <td class="text-center align-middle"> <b> Accept revision </b></td>
                            <td class="text-center align-middle"> <b> Decline revision </b></td>
                        </tr>
                        <AdminRevisionRow v-for="revision in this.revisions" :revision="revision" :key="revision.id"> </AdminRevisionRow>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import AdminRevisionRow from '@/components/AdminRevisionRow.vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import Vue from 'vue'
    import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: 'AdminRevisionsView',
        components: {
            NavbarAdmin,
            AdminRevisionRow
        },
        data(){
            return {
                revisions: []
            }
        },
        mounted () {
            if (window.sessionStorage.getItem('role') === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
                axios.get("http://localhost:8088/revisions/findAll",{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.revisions = response.data
                })
            }else {
                router.push("/");
            }
        }
    }
</script>