<template>
    <div class="d-flex flex-row">
        <NavbarAdmin></NavbarAdmin>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Ships </th>
                    </thead>
                    <tbody>
                        <AdminServiceRow v-for="ship in this.ships" :service="ship" :key="ship.id"> </AdminServiceRow>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Retreats </th>
                    </thead>
                    <tbody>
                        <AdminServiceRow v-for="retreat in this.retreats" :service="retreat" :key="retreat.id"> </AdminServiceRow>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Adventures </th>
                    </thead>
                    <tbody>
                        <AdminServiceRow v-for="adventure in this.adventures" :service="adventure" :key="adventure.id"> </AdminServiceRow>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import NavbarAdmin from "@/components/NavbarAdmin.vue"
    import AdminServiceRow from "@/components/AdminServiceRow.vue"
import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: 'AdminServicesView',
        components: {
            NavbarAdmin,
            AdminServiceRow
        },
        data (){
            return {
                ships: [],
                retreats: [],
                adventures: []
            }
        },
        mounted () {
            if (window.sessionStorage.getItem('role') === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
                axios.get("http://localhost:8088/ships/getAll").then((response) => {
                    this.ships = response.data
                }),
                axios.get("http://localhost:8088/retreats/getAll").then((response) => {
                    this.retreats = response.data
                }),
                axios.get("http://localhost:8088/adventures/getAll").then((response) => {
                    this.adventures = response.data
                })
            }
            else {
                router.push("/");
            }
        }
    }
</script>
