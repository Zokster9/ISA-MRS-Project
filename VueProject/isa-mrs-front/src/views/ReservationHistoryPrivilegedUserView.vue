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
                        <ReservationHistoryPrivilegedUserRow v-for="reservation in reservations" :reservation="reservation" :key="reservation.id"></ReservationHistoryPrivilegedUserRow>
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

    Vue.use(VueAxios, axios)

    export default {
        name: 'ReservationHistoryPrivilegedUser',
        components: {
            NavbarUser,
            ReservationHistoryPrivilegedUserRow
        },
        data(){
            return{
                reservations: []
            }
        },
        methods: {

        },
        mounted(){
            axios.get("http://localhost:8088/reservations/getPrivilegedUserReservations", {
				headers: {
					Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
				}
			}).then((response) =>{
                this.reservations = response.data
            })
        },
    }
</script>
