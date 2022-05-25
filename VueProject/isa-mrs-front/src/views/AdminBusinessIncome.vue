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
                        <th colspan="100%" class="text-center h2">Income change and business reports</th> 
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center align-middle"> Income from:<br>
                                <input type="date" v-model="this.fromDate">
                            </td>
                            <td class="text-center align-middle">Income till:<br>
                                <input type="date" v-model="this.toDate">
                            </td>
                            <td class="text-center align-middle">
                                <button type="button" class="btn btn-primary" @click="search">Search</button>
                            </td>
                            <td class="text-center align-middle">
                                <button type="button" class="btn btn-primary" @click="showAll">Show all</button>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-center align-middle"> <b> Id </b></td>
                            <td class="text-center align-middle"> <b> From date </b></td>
                            <td class="text-center align-middle"> <b> To date </b></td>
                            <td class="text-center align-middle"> <b> Price </b></td>
                        </tr>
                        <tr v-for="reservation in this.reservations" :key="reservation.id">
                            <td class="align-middle text-center"> {{reservation.id}} </td>
                            <td class="align-middle text-center"> {{ reservation.fromDate}} </td>
                            <td class="align-middle text-center"> {{ reservation.toDate}} </td>
                            <td class="align-middle text-center"> {{ reservation.price}} </td>
                        </tr>
                        <tr>
                            <td class="align-middle text-center" colspan="3"><b> Total system income: </b></td>
                            <td class="align-middle text-center"><b> {{this.totalIncome}} </b></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<script>
    import NavbarAdmin from '@/components/NavbarAdmin.vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import Vue from 'vue'

    Vue.use(VueAxios, axios)

    export default {
        name: 'AdminBusinessIncome',
        components: {
            NavbarAdmin
        },
        data(){
            return {
                reservations: [],
                fromDate: null,
                toDate: null,
                totalIncome: 50
            }
        },
        methods:{
            search(){
                
            },
            showAll(){
                axios.get("http://localhost:8088/reservations/findAllNotCancelled",{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.reservations = response.data;
                })
            }
        },
        mounted () {
            axios.get("http://localhost:8088/reservations/findAllNotCancelled",{
                headers:{
                    Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                }
            }).then((response) =>{
                this.reservations = response.data;
            })
        }
    }
</script>
