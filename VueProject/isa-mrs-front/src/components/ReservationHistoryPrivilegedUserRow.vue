<template>
    <tr :key="reservation.id">
        <td class="align-middle text-center">
            <figcaption class="mb-1"> {{reservation.serviceName}} </figcaption>
            <router-link exact :to="linkToService">
                <img :src="require('@/assets/' + reservation.servicePictures[0])" style="width:200px; height:200px;" class="rounded">
            </router-link>
        </td>
        <td class="align-middle text-center">
            <p> Name and surname: {{reservation.clientName}}  {{reservation.clientSurname}} </p>
            <p> Address: {{reservation.clientCountry}}, {{reservation.clientCity}}, {{reservation.clientStreet}} </p>
            <p> Email: {{reservation.clientEmail}} </p>
        </td>
        <td class="align-middle text-center">
            <p> From: {{reservation.fromDate}} {{reservation.fromTime}} </p>
            <p> To: {{reservation.toDate}} {{reservation.toTime}} </p>   
        </td>
        <td class="align-middle text-center">
            <p> {{reservation.price}} euros </p>
        </td>
        <td class="align-middle text-center">
            <p> {{reservation.status}} </p>
        </td>
        <td class="align-middle text-center" v-if="reservationData.status == 'Finished'">
            <button type="button" class="btn btn-primary" @click="writeReport"> Write a report </button>
        </td>
        <td class="align-middle text-center" v-if="reservationData.status == 'Pending'">
            <button type="button" class="btn btn-primary" @click="endReservation"> End reservation </button>
        </td>
    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        props: ["reservation"],
        methods: {
            endReservation(){
                axios.put("http://localhost:8088/reservations/changeStatus",{
                    id: this.reservationData.id,
                    fromDate: this.reservationData.fromDate,
                    toDate: this.reservationData.toDate,
                    fromTime: this.reservationData.fromTime,
                    toTime: this.reservationData.toTime,
                    price: this.reservationData.price,
                    status: this.reservationData.status,
                    userRating: this.reservationData.userRating,
                    serviceRating: this.reservationData.serviceRating,
                    serviceId: this.reservationData.serviceId,
                    clientName: this.reservationData.clientName,
                    clientSurname: this.reservationData.clientSurname,
                    clientEmail: this.reservationData.clientEmail,
                    clientCountry: this.reservationData.clientCountry,
                    clientCity: this.reservationData.clientCity,
                    clientStreet: this.reservationData.clientStreet,
                    serviceName: this.reservationData.serviceName,
                    servicePictures: this.reservationData.servicePictures,
                    clientId: this.reservationData.clientId,
                    privilegedUserId: this.reservationData.privilegedUserId
                },
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    window.location.reload();
                })
            },
            writeReport(){
                router.push('/write-report/' + this.reservationData.id);
            }
        },
        data(){
            return {
                reservationData: this.reservation,
                linkToService: ""
            }
        },
        mounted(){
            if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
                this.linkToService = "/retreat/" + this.reservation.serviceId;
            }else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") {
                this.linkToService = "/ship/" + this.reservation.serviceId;
            }else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                this.linkToService = "/adventure/"+ this.reservation.serviceId;
            }else {
                alert('Some kind of error happened!');
            }
        }  
    }
</script>