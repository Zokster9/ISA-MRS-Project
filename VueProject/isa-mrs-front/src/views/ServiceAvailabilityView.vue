<template>
    <div>
        <NavbarUser></NavbarUser>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Add new service availability </th>
                    </thead>
                    <tbody>
                        <ServiceAvailabilityRow v-for="service in services" :service="service" :key="service.id"></ServiceAvailabilityRow>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<script>
    import ServiceAvailabilityRow from '@/components/ServiceAvailabilityRow.vue'
    import NavbarUser from '@/components/NavbarUser.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: 'ServiceAvailability',
        components: {
            ServiceAvailabilityRow,
            NavbarUser
        },
        data(){
            return{
                services: []
            }
        },
        mounted(){
            if (window.sessionStorage.getItem('role') === "ROLE_retreatOwner" || window.sessionStorage.getItem("role") === "ROLE_shipOwner" || window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                axios.get("http://localhost:8088/users/findMyEntities", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.services = response.data
                })
            }
            else {
                router.push("/");
            }
        },
    }
</script>