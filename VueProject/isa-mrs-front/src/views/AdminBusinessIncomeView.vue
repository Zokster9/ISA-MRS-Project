<template>
    <div>
        <NavbarAdmin></NavbarAdmin>
        <div class="row justify-content-center mx-auto my-4">
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
                                <input type="date" v-model="fromDate">
                            </td>
                            <td class="text-center align-middle">Income till:<br>
                                <input type="date" v-model="toDate">
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
                            <td class="align-middle text-center"> {{ getDate(reservation.fromDate)}} </td>
                            <td class="align-middle text-center"> {{ getDate(reservation.toDate)}} </td>
                            <td class="align-middle text-center"> {{ reservation.price}} </td>
                        </tr>
                        <tr>
                            <td class="align-middle text-center" colspan="3"><b> Total system income: </b></td>
                            <td class="align-middle text-center"><b> {{this.totalIncome}} </b></td>
                        </tr>
                        <tr>
                            <td class="align-middle text-center" colspan="2"> Income percentage from reservations: </td>
                            <td class="align-middle text-center">
                                <input type="number" placeholder="Value between 0 and 100" v-model="newPercentage">
                            </td>
                            <td class="align-middle text-center">
                                <button type="button" class="btn btn-primary" @click="updatePercentage" :disabled="$v.newPercentage.$invalid"> Confirm</button>
                            </td>
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
    import Vuelidate from 'vuelidate'
    import { required, minValue, maxValue } from 'vuelidate/lib/validators'
    import router from '@/router'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

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
                totalIncome: 0,
                newPercentage: ""
            }
        },
        methods:{
            updatePercentage(){
                axios.post("http://localhost:8088/discounts/updatePercentage",{
                    discount: this.newPercentage,
                    fromDate: new Date(),
                    toDate: new Date('2100-01-01 02:00:00')
                },{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    },
                }).then(() => {
                    alert("Successfully changed percentage taken of system reservations!")
                    window.location.reload();
                })
            },
            search(){
                axios.get("http://localhost:8088/reservations/findInDateSpan?fromDate=" + this.fromDate + "&toDate=" + this.toDate,{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    },
                }).then((response) => {
                    this.reservations = response.data;
                    axios.post("http://localhost:8088/reservations/calculateSystemIncome", {
                        reservationsDTO: this.reservations,
                    },{
                        headers:{
                            Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                        }
                    }).then((response) => {
                        this.totalIncome = response.data;
                    })
                })
            },
            showAll(){
                axios.get("http://localhost:8088/reservations/findAllNotCancelled",{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.reservations = response.data;
                    axios.post("http://localhost:8088/reservations/calculateSystemIncome", {
                        reservationsDTO: this.reservations,
                    },{
                        headers:{
                            Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                        }
                    }).then((response) => {
                        this.totalIncome = response.data;
                    })
                })
            },
            getDate (date) {
                let origin_date = new Date(date)
                let month = origin_date.getMonth() + 1
                if (month < 10) {
                    month = '0' + month
                }
                return origin_date.getFullYear() + '/' + month + '/' + origin_date.getDate()
            }
        },
        mounted () {
            if (window.sessionStorage.getItem('role') === "ROLE_admin" || window.sessionStorage.getItem("role") === "ROLE_mainAdmin") {
                axios.get("http://localhost:8088/reservations/findAllNotCancelled",{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.reservations = response.data;
                    axios.post("http://localhost:8088/reservations/calculateSystemIncome", {
                        reservationsDTO: this.reservations,
                    },{
                        headers:{
                            Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                        }
                    }).then((response) => {
                        this.totalIncome = response.data;
                    })
                })
            }else {
                router.push("/");
            }
        },
        validations: {
            newPercentage: {
                required,
                minValue: minValue(0),
                maxValue: maxValue(100)
            }
        }
    }
</script>
