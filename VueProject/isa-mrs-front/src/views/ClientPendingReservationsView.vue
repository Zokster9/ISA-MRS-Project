<template>
    <div class="d-flex flex-row">
        <div>
            <NavbarClient></NavbarClient>
        </div>
        <div class="mx-auto" style="margin-top: 80px; width:50%">
            <div v-if="pendingReservations">
                <div v-if="pendingReservations.length != 0">
                    <ReservationCard v-for="reservation in pendingReservations" @cancel="cancelReservation" :reservation="reservation" :key="reservation.id"></ReservationCard>
                </div>
                <div v-else>
                    <h1>There are no pending reservations for you.</h1>
                </div>
            </div>
            <div v-else>
                <h1>There are no pending reservations for you.</h1>
            </div>
        </div>
    </div>
</template>

<script>
    import NavbarClient from '@/components/NavbarClient.vue'
    import ReservationCard from '@/components/ReservationCard.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        name: "ClientPendingReservations",
        components: {
            NavbarClient,
            ReservationCard,
        },
        data () {
            return {
                pendingReservations: null,
            }
        },
        methods: {
            cancelReservation(reservationId) {
                axios.put("http://localhost:8088/reservations/cancelReservation/" + reservationId, {},
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(() => {
                    alert("Reservation successfully cancelled");
                    this.pendingReservations = this.pendingReservations.filter(pendingReservation => pendingReservation.id != reservationId)
                })
                .catch(() => {
                    alert("This reservation cannot be cancelled.")
                })
            },
            getPendingReservations() {
                axios.get("http://localhost:8088/reservations/getPendingReservations", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(response => {
                    this.pendingReservations = response.data;
                })
                .catch(() => {
                    alert("Something went wrong!");
                })
            }
        },
        mounted () {
            if (window.sessionStorage.getItem("role") === "ROLE_client") {
                this.getPendingReservations();
            }
            else {
                router.push("/");
            }
        }
    }
</script>