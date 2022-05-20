<template>
    <tr :key="report.id" :class="{activeAcceptButton: hoverAcceptButton, activeDeclineButton: hoverDeclineButton}">
        <td class="align-middle text-center"> {{ report.clientName }} {{report.clientSurname}}</td>
        <td class="align-middle text-center"> {{ report.ownerName }} {{report.ownerSurname}}</td>
        <td class="align-middle text-center">
            <figcaption class="mb-1"> {{report.serviceName}} </figcaption> 
            <img :src="require('@/assets/' + report.servicePictures[0])" style="width:200px; height:200px" class="mb-1"> 
        </td>
        <td class="align-middle text-center w-25">  {{report.report}} </td>
        <td class="align-middle text-center">
            <button type="button" class="btn btn-success" @mouseover="hoverAcceptButton = true" 
            @mouseleave="hoverAcceptButton = false" @click="acceptReport(report.id, report.clientName, report.clientSurname, report.ownerName,
             report.ownerSurname, report.serviceName, report.servicePictures, report.report, report.reservationId)" >
             Accept report</button>
        </td>
        <td class="align-middle text-center">
            <button type="button" class="btn btn-danger" @mouseover="hoverDeclineButton = true" 
            @mouseleave="hoverDeclineButton = false" @click="declineReport(report.id, report.clientName, report.clientSurname, report.ownerName,
             report.ownerSurname, report.serviceName, report.servicePictures, report.report, report.reservationId)" >
             Decline report</button>
        </td>

    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    Vue.use(VueAxios, axios)
    export default {
        props: ["report"],
        methods: {
            acceptReport(id, clientName, clientSurname, ownerName, ownerSurname, serviceName, servicePictures, report, reservationId){
                axios.put("http://localhost:8088/reports/updateReport", {
                    id: id,
                    clientName: clientName,
                    clientSurname: clientSurname,
                    ownerName: ownerName,
                    ownerSurname: ownerSurname,
                    serviceName: serviceName,
                    servicePictures: servicePictures,
                    report: report,
                    isNegative: true,
                    hasShowedUp: true,
                    isAnswered: true,
                    isPenalized: true,
                    reservationId: reservationId
                },{
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    alert("Report has been accepted.")
                    window.location.reload();
                })
            },
            declineReport(id, clientName, clientSurname, ownerName, ownerSurname, serviceName, servicePictures, report, reservationId){
                axios.put("http://localhost:8088/reports/updateReport", {
                    id: id,
                    clientName: clientName,
                    clientSurname: clientSurname,
                    ownerName: ownerName,
                    ownerSurname: ownerSurname,
                    serviceName: serviceName,
                    servicePictures: servicePictures,
                    report: report,
                    isNegative: true,
                    hasShowedUp: true,
                    isAnswered: true,
                    isPenalized: false,
                    reservationId: reservationId
                },{
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    alert("Report has been declined.")
                    window.location.reload();
                })
            }
        },
        data(){
            return {
                hoverAcceptButton: false,
                hoverDeclineButton: false,
            }
        },
    }
</script>