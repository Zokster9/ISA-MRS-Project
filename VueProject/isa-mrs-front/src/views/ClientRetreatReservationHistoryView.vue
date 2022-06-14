<template>
    <div style="width: 100%; height: 100%;">
        <NavbarClient></NavbarClient>
        <div class="d-flex">
            <ReservationHistorySidebar @sort="sort" style="background-color: #ffffff;"></ReservationHistorySidebar>
            <div style="width:50%;height:100%;margin:auto;margin-top:110px;">
                <div v-if="finishedReservations" class="vertical-center">
                    <div v-if="orderedResults.length != 0">
                        <ReservationCard @review="review" v-for="reservation in orderedResults" :reservation="reservation" :key="reservation.id"></ReservationCard>
                    </div>
                    <div v-else>
                        <h1>There are no finished retreat reservations for you.</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import _orderBy from 'lodash/orderBy';
    import NavbarClient from '@/components/NavbarClient.vue'
    import ReservationHistorySidebar from '@/components/ReservationHistorySidebar.vue'
    import ReservationCard from '@/components/ReservationCard.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
import router from '@/router';

    Vue.use(VueAxios, axios)

    export default {
        name: "ClientShipReservationHistory",
        components: {
            NavbarClient,
            ReservationHistorySidebar,
            ReservationCard,
        },
        data () {
            return {
                finishedReservations: null,
                sortBy: 'price',
            }
        },
        computed: {
            orderedResults() {
                return _orderBy(this.finishedReservations, this.sortBy)
            },
        },
        methods: {
            review(id) {
                this.$router.push("/review-reservation/" + id);
            },
            sort(sort) {
                this.sortBy = sort;
            }
        },
        mounted () {
            if (window.sessionStorage.getItem("role") === "ROLE_client") {
                axios.get("http://localhost:8088/reservations/retreatReservationHistory", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                })
                .then(response => {
                    this.finishedReservations = response.data
                })
                .catch(() => {
                    alert("Something went wrong!")
                })
            }
            else {
                router.push("/");
            }
        }
    }
</script>