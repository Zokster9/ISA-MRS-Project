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
                        <th colspan="100%" class="text-center h2"> Account Termination Requests </th>
                    </thead>
                    <tbody>
                        <TerminationRequestRow v-for="terminationRequest in terminationRequests" :termination="terminationRequest" :key="terminationRequest.id"></TerminationRequestRow>
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
        mounted() {
            axios.get("http://localhost:8088/termination/findToTerminate",{
                headers:{
                    Authorization: 'Bearer ' + window.localStorage.getItem("accessToken")
                }
            }).then((response) => {
                this.terminationRequests = response.data;
            })
        },
    }
</script>