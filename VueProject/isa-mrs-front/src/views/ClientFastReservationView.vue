<template>
    <div v-if="actions" class="d-flex flex-row">
        <div>
            <NavbarClient></NavbarClient>
        </div>
        <div v-if="actions.length != 0" class="mx-auto" style="margin-top: 80px;">
            <FastReservationClient @reserve="reserve" v-for="action in actions" :action="action" :key="action.id"></FastReservationClient>
        </div>
        <div v-else class="mx-auto" style="margin-top: 80px;">
            <h1>There are no fast reservations available</h1>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import NavbarClient from '@/components/NavbarClient.vue'
    import FastReservationClient from '@/components/FastReservationInfo.vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router'

    Vue.use(VueAxios, axios)
    
    export default {
        name: "ClientFastReservation",
        components: {
            NavbarClient,
            FastReservationClient,
        },
        data() {
            return {
                actions: null,
            }
        },
        methods: {
            getActions() {
                axios.get("https://isa-project-tim3.herokuapp.com/services/getAvailableFastReservations/" + this.$route.params.id, {
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(response => {
                    this.actions = response.data;
                })
                .catch(() => {
                    alert("Something went wrong!")
                })
            },
            getDate (date) {
                let origin_date = new Date(date)
                let month = origin_date.getMonth() + 1
                if (month < 10) {
                    month = '0' + month
                }
                let day = origin_date.getDate()
                if (day < 10) {
                    day = '0' + day
                }
                return origin_date.getFullYear() + '-' + month + '-' + day
            },
            reserve(action) {
                let fromDate = new Date(action.dateFrom);
                let toDate = new Date(action.dateTo)
                fromDate.setHours(0, 0, 0, 0);
                toDate.setHours(0, 0, 0, 0);
                console.log(fromDate)
                console.log(toDate)
                axios.post("https://isa-project-tim3.herokuapp.com/reservations/makeAReservation", {
                    fromDate: fromDate,
                    toDate: toDate,
                    fromTime: action.timeFrom,
                    toTime: action.timeTo,
                    price: action.price,
                    serviceId: this.$route.params.id,
                    numOfPeople: action.maxNumOfPeople,
                    additionalServices: action.additionalServices,
                    serviceType: null,
                },
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(() => {
                    alert("Reservation is successful. Check your email.")
                    this.getActions();
                })
                .catch(() => {
                    alert("Something went wrong!")
                })
            }
        },
        mounted() {
            if (window.sessionStorage.getItem("role") === "ROLE_client") {
                this.getActions();
            }
            else {
                router.push("/");
            }
        }
    }
</script>