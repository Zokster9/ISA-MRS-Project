<template>
    <div class="d-flex flex-row">
        <div class="row justify-content-center mx-auto">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2">My income</th> 
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
                            <td class="align-middle text-center" colspan="3"><b> My income: </b></td>
                            <td class="align-middle text-center"><b> {{this.totalIncome}} </b></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<script>
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import { required, minValue, maxValue } from 'vuelidate/lib/validators'
    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)
    export default {
        data(){
            return {
                reservations: [],
                fromDate: null,
                toDate: null,
                totalIncome: 0,
            }
        },
        methods:{
            search(){
                axios.get("http://localhost:8088/reservations/findInDateSpanPrivilegedUser?fromDate=" + this.fromDate + "&toDate=" + this.toDate,{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    },
                }).then((response) => {
                    this.reservations = response.data;
                    axios.post("http://localhost:8088/reservations/calculateMyIncome", {
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
                axios.get("http://localhost:8088/reservations/findUsersNonCancelledReservations",{
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.reservations = response.data;
                    axios.post("http://localhost:8088/reservations/calculateMyIncome", {
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
            axios.get("http://localhost:8088/reservations/findUsersNonCancelledReservations",{
                headers:{
                    Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                }
            }).then((response) =>{
                this.reservations = response.data;
                axios.post("http://localhost:8088/reservations/calculateMyIncome", {
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
        validations: {
            newPercentage: {
                required,
                minValue: minValue(0),
                maxValue: maxValue(100)
            }
        }
    }
</script>
