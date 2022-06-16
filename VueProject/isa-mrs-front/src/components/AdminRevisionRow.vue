<template>
    <tr :key="revision.id" :class="{activeAcceptButton: hoverAcceptButton, activeDeclineButton: hoverDeclineButton}">
        <td class="align-middle text-center"> {{ revision.clientName }} {{revision.clientSurname}}</td>
        <td class="align-middle text-center"> {{ revision.ownerName }} {{revision.ownerSurname}}</td>
        <td class="align-middle text-center display-4"> <b> {{ revision.ownerRating }} </b> </td>
        <td class="align-middle text-center">
            <figcaption class="mb-1"> {{revision.serviceName}} </figcaption> 
            <img :src="require('@/assets/' + revision.servicePictures[0])" style="width:200px; height:200px" class="mb-1"> 
        </td>
        <td class="align-middle text-center display-4"> <b> {{revision.serviceRating}}  </b> </td>
        <td class="align-middle text-center w-25">  {{revision.revision}} </td>
        <td class="align-middle text-center">
            <button type="button" class="btn btn-success" @mouseover="hoverAcceptButton = true" 
            @mouseleave="hoverAcceptButton = false" @click="acceptRevision(revision.id, revision.clientName, revision.clientSurname, revision.ownerName,
             revision.ownerSurname, revision.serviceName, revision.servicePictures, revision.revision, revision.ownerRating, revision.serviceRating)" >
             Accept revision</button>
        </td>
        <td class="align-middle text-center">
            <button type="button" class="btn btn-danger" @mouseover="hoverDeclineButton = true" 
            @mouseleave="hoverDeclineButton = false" @click="declineRevision(revision.id, revision.clientName, revision.clientSurname, revision.ownerName,
             revision.ownerSurname, revision.serviceName, revision.servicePictures, revision.revision, revision.ownerRating, revision.serviceRating)" >
             Decline revision</button>
        </td>

    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    Vue.use(VueAxios, axios)

    export default {
        props: ["revision"],
        methods: {
            acceptRevision(id, clientName, clientSurname, ownerName, ownerSurname, serviceName, servicePictures, revision, ownerRating, serviceRating){
                axios.post("http://localhost:8088/revisions/updateRevision", {
                    id: id,
                    isAnswered: true,
                    clientName: clientName,
                    clientSurname: clientSurname,
                    ownerName: ownerName,
                    ownerSurname: ownerSurname,
                    serviceName: serviceName,
                    servicePictures: servicePictures,
                    revision: revision,
                    ownerRating: ownerRating,
                    serviceRating: serviceRating,
                    isApproved: true
                },{
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    window.location.reload();
                }).catch((error) => {
                    if (error.response.status === 409) alert("Error. Some other administrator already replied to this revision request.");
                    else alert("Error occured. Accepting of a revision wasn't successful.");
                });
            },
            declineRevision(id, clientName, clientSurname, ownerName, ownerSurname, serviceName, servicePictures, revision, ownerRating, serviceRating){
                axios.post("http://localhost:8088/revisions/updateRevision", {
                    id: id,
                    isAnswered: true,
                    clientName: clientName,
                    clientSurname: clientSurname,
                    ownerName: ownerName,
                    ownerSurname: ownerSurname,
                    serviceName: serviceName,
                    servicePictures: servicePictures,
                    revision: revision,
                    ownerRating: ownerRating,
                    serviceRating: serviceRating,
                    isApproved: false
                },{
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    window.location.reload();
                }).catch((error) => {
                    if (error.response.status === 409) alert("Error. Some other administrator already replied to this revision request.");
                    else alert("Error occured. Declining of a revision wasn't successful.");
                });
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