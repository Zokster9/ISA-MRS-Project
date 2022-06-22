<template>
    <div>
        <NavbarUser></NavbarUser>
        <div class="row justify-content-center mx-auto my-4">
            <div class="col-auto">
                <br>
                <br>
                <table class="table table-striped table-responsive table-bordered mb-5">
                    <thead>
                        <th colspan="100%" class="text-center h2"> Service reservation history </th>
                    </thead>
                    <tbody>
                        <tr class="align-middle text-center">
                            <td> <b> Service name </b> </td>
                            <td> <b> Client info </b> </td>
                            <td> <b> Reservation length </b> </td>
                            <td> <b> Price </b> </td>
                            <td> <b> Reservation status </b> </td>
                            <td> <b> Action </b> </td>
                        </tr>
                        <ReservationHistoryPrivilegedUserRow v-for="reservation in currentPageReservations" :reservation="reservation" :key="reservation.id"></ReservationHistoryPrivilegedUserRow>
                        <tr>
                            <td colspan="5"></td>
                            <td>
                                <PaginationComponent :elements="this.reservations" v-on:page:update="updatePage" :currentPage="this.currentPage" :pageSize="this.pageSize"></PaginationComponent>
                            </td>
                        </tr>
                    </tbody>
                </table>                                  
            </div>
        </div>
    </div>    
</template>
<script>

    import NavbarUser from '@/components/NavbarUser.vue'
    import ReservationHistoryPrivilegedUserRow from '@/components/ReservationHistoryPrivilegedUserRow.vue'
    import Vue from 'vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'
    import PaginationComponent from '@/components/PaginationComponent.vue'

    Vue.use(VueAxios, axios)

    export default {
        name: 'ReservationHistoryPrivilegedUser',
        components: {
            NavbarUser,
            ReservationHistoryPrivilegedUserRow,
            PaginationComponent
        },
        data(){
            return{
                currentPage: 0,
                pageSize: 5,
                reservations: [],
                currentPageReservations: []
            }
        },
        methods: {
            updateVisibleReservations(){
                //1. param: pocetni index, 2. param: index do kojeg prikazujemo
                this.currentPageReservations = this.reservations.slice(this.currentPage * this.pageSize, (this.currentPage * this.pageSize) + this.pageSize);
                if (this.currentPageReservations.length == 0 && this.currentPage > 0){
                    this.updatePage(this.currentPage - 1);
                }
            },
            updatePage(pageNumber){
                this.currentPage = pageNumber;
                this.updateVisibleReservations();
            },
        },
        mounted(){
            if (window.sessionStorage.getItem('role') === "ROLE_retreatOwner" || window.sessionStorage.getItem("role") === "ROLE_shipOwner" || window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                axios.get("http://localhost:8088/reservations/getPrivilegedUserReservations", {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response) =>{
                    this.reservations = response.data
                    this.updateVisibleReservations();
                })
            }
            else {
                router.push("/");
            }
        },
    }
</script>
