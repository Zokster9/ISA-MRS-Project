<template>
    <div>
        <NavbarAdmin></NavbarAdmin>
        <div class="row justify-content-center my-5">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Account Termination Requests </th>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center"> <b> Name and surname </b> </td>
                            <td class="text-center"> <b> Termination reasoning </b> </td>
                            <td class="text-center" colspan="2"> <b> OPTIONS </b> </td>  
                        </tr>
                        <TerminationRequestRow @removeTermination="removeTermination" v-for="terminationRequest in terminationRequests" :termination="terminationRequest" :key="terminationRequest.id"></TerminationRequestRow>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import TerminationRequestRow from '@/components/AccountTerminationRow.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: "AccountTerminationRequests",
        components: {
            NavbarAdmin,
            TerminationRequestRow,
        },
        data() {
            return {
                terminationRequests: [],
            }
        },
        methods: {
            removeTermination(id) {
                this.terminationRequests = this.terminationRequests.filter(terminationRequest => id != terminationRequest.userId)
            }
        },
        mounted() {
            if (window.sessionStorage.getItem('role') === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
                axios.get("https://isa-project-tim3.herokuapp.com/termination/findToTerminate",{
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) => {
                    this.terminationRequests = response.data;
                });
            }else {
                router.push("/");
            }
        },
    }
</script>