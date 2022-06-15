<template>
    <tr :key="service.id" :class="{activeDeleteButton: hoverDeleteButton, activeEditButton: hoverEditButton, activeAcceptButton: hoverNewActionButton}">
        <td class="align-middle text-center">
            <figcaption class="mb-1"> {{service.name}} </figcaption>
            <router-link exact :to="linkToService">
                <img :src="require('@/assets/' + service.pictures[0])" style="width:200px; height:200px;" class="rounded">
            </router-link>
        </td>
        <td class="align-middle text-center"> <button type="button" @mouseover="hoverEditButton = true" @mouseleave="hoverEditButton = false" class="btn btn-warning" @click="editService(service.id)">Edit service info</button></td>
        <td class="align-middle text-center"> <button type="button" @mouseover="hoverDeleteButton = true" @mouseleave="hoverDeleteButton = false" class="btn btn-danger" @click="deleteService(service.id)">Delete service</button></td>
        <td class="align-middle text-center"> <button type="button" @mouseover="hoverNewActionButton = true" @mouseleave ="hoverNewActionButton = false" class="btn btn-success" @click="newAction(service.id)"> New action </button></td>
    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        props: ["service"],
        methods: {
            newAction(id){
                router.push('/add-action/' + id)
            },
            //router link na stranicu sa servisom koji ima dati ID
            editService(id){
                this.id = id;
                if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
                    router.push('/retreat-info/' + id);
				}else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") {
                    router.push('/ship-info/' + id);
				}else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                    router.push('/update-adventure/' + id);
				}else {
                    alert('Some kind of error happened!');
                }
            },
            //poziv na back da izbrise servis
            deleteService(id){
                this.id = id;
                if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
                    this.deleteRetreat(id);
				}else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") {
                    this.deleteShip(id);
				}else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                    this.deleteAdventure(id);
				}else {
                    alert('Some kind of error happened!');
                }
            },
            deleteRetreat(id) {
                axios.delete('http://localhost:8088/retreats/delete-retreat/' + id, {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                    }
                }).then(() => {
                    alert("Retreat successfully deleted.");
                    window.location.reload();
                }).catch((error) => {
                    if (error.response.status === 409) alert("Error. Pending reservation for retreat exists.");
                    else alert("Error occured. You can not delete this retreat.");
                })
            },
            deleteShip(id) {
                axios.delete('http://localhost:8088/ships/delete-ship/' + id, {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem('accessToken')
                    }
                }).then(() => {
                    alert("Ship successfully deleted.");
                    window.location.reload();
                }).catch((error) => {
                    if (error.response.status === 409) alert("Error. Pending reservation for ship exists.");
                    else alert("Error occured. You can not delete this ship.");
                })
            },
            deleteAdventure(id) {
                axios.delete('http://localhost:8088/adventures/delete-adventure/' + id,
                {
                    headers: {
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() =>{
                    alert("Adventure successfully deleted.")
                    window.location.reload()
                }).catch((error) => {
                    if (error.response.status === 409) alert("Error. Pending reservation for adventure exists.");
                    else alert("Error occured. You can not delete this adventure.");
                })
            },
            showService(id) {
                if (window.sessionStorage.getItem('role') === "ROLE_retreatOwner") router.push("/retreat/" + id);
                else if (window.sessionStorage.getItem('role') === "ROLE_shipOwner") router.push("/ship/" + id);
                else if (window.sessionStorage.getItem('role') === "ROLE_fishingInstructor") router.push("/adventure/" + id);
            }
        },
        data(){
            return {
                hoverEditButton: false,
                hoverDeleteButton: false,
                hoverNewActionButton: false,
                linkToService: ""
            }
        },
        mounted(){
            if (window.sessionStorage.getItem("role") === "ROLE_retreatOwner") {
                this.linkToService = "/retreat/" + this.service.id;
            }else if (window.sessionStorage.getItem("role") === "ROLE_shipOwner") {
                this.linkToService = "/ship/" + this.service.id;
            }else if (window.sessionStorage.getItem("role") === "ROLE_fishingInstructor") {
                this.linkToService = "/adventure/" + this.service.id;
            }else {
                alert('Some kind of error happened!');
            }
        }        
    }
</script>