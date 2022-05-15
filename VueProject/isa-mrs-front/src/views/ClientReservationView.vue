<template>
    <div style="width: 100%; height: 100%;">
        <NavbarClient></NavbarClient>
        <div class="d-flex">
            <ReservationSidebar @search="search" @sort="sort" style="background-color: #ffffff;"></ReservationSidebar>
            <div style="width:50%;height:100%;margin:auto;margin-top:110px;">
                <div v-if="availableReservations" class="vertical-center">
                    <SearchReservationResult v-for="reservationResult in orderedResults" :searchResult="reservationResult" :serviceType="type" :key="reservationResult.id"></SearchReservationResult>
                </div>
                <div v-else class="mx-auto" style="margin-top: 50px;">
                    <h1>Here you can see available services for reservations when you enter your desired parameters.</h1>
                </div>
            </div>
        </div>
        
    </div>
</template>

<script>
    import _orderBy from 'lodash/orderBy';
    import NavbarClient from '@/components/NavbarClient.vue'
    import ReservationSidebar from '@/components/ReservationSidebar.vue'
    import SearchReservationResult from '@/components/SearchReservationResult.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        name: 'ClientReservation',
        components: {
            NavbarClient,
            ReservationSidebar,
            SearchReservationResult,
        },
        data () {
            return {
                availableReservations: null,
                sortBy: 'price',
                type: "",
            }
        },
        computed: {
            orderedResults() {
                return _orderBy(this.availableReservations, this.sortBy)
            }
        },
        methods: {
            search(reservationForm) {
                this.type = reservationForm.serviceType;
                console.log(reservationForm.startTime < reservationForm.endTime)
                console.log(reservationForm)
            },
            sort(sort) {
                this.sortBy = sort;
                console.log(this.sortBy)
            }
        },
        mounted () {
            axios.get("http://localhost:8088/ships/getAll")
            .then(response => {
                this.availableReservations = response.data;
            })
            .catch(() => {
                alert("Something went wrong!");
            });
        }
    }
</script>