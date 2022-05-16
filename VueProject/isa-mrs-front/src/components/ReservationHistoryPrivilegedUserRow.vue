<template>
    <tr :key="reservation.id">
        <td class="align-middle text-center">
            <figcaption class="mb-1"> {{reservation.serviceName}} </figcaption>
            <img :src="require('@/assets/' + reservation.servicePictures[0])" style="width:200px; height:200px;" class="rounded">
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
    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        props: ["reservation"],
        methods: {

        },
        data(){
            return {
            }
        },        
    }
</script>