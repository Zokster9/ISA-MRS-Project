<template>
    <tr :key="service.id" :class="{activeDeleteButton: hoverDeleteButton, activeEditButton: hoverEditButton, activeAcceptButton: hoverNewActionButton}">
        <td class="align-middle text-center"> {{service.name}} </td>
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
                if (window.localStorage.getItem("role") === "ROLE_retreatOwner") {
                    router.push('/retreat-info/' + id);
				}else if (window.localStorage.getItem("role") === "ROLE_shipOwner") {
                    router.push('/ship-info/' + id);
				}else if (window.localStorage.getItem("role") === "ROLE_fishingInstructor") {
                    router.push('/update-adventure/' + id);
				}else {
                    alert('Some kind of error happened!');
                }
            },
            //poziv na back da izbrise servis
            deleteService(id){
                this.id = id;
                if (window.localStorage.getItem("role") === "ROLE_retreatOwner") {
                    this.deleteRetreat(id);
				}else if (window.localStorage.getItem("role") === "ROLE_shipOwner") {
                    this.deleteShip(id);
				}else if (window.localStorage.getItem("role") === "ROLE_fishingInstructor") {
                    this.deleteAdventure(id);
				}else {
                    alert('Some kind of error happened!');
                }
            },
            deleteRetreat(id) {
                axios.delete('http://localhost:8088/retreats/delete-retreat/' + id, {
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                }).then(() => {
                    alert("Retreat successfully deleted.");
                    window.location.reload();
                })
            },
            deleteShip(id) {
                axios.delete('http://localhost:8088/ships/delete-ship/' + id, {
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                }).then(() => {
                    alert("Ship successfully deleted.");
                    window.location.reload();
                })
            },
            deleteAdventure() {
                let path = "http://localhost:8088/adventures/deleteService"; 
                axios.post(path,
                {
                    id: this.service.id,
                    name: this.service.name,
                    description: this.service.description,
                    pictures: this.service.pictures
                }, 
                {
                    headers: {
                        Authorization: 'Bearer ' + window.localStorage.getItem("accessToken")
                    }
                }).then(() =>{
                    window.location.reload()
                })
            }
        },
        data(){
            return {
                hoverEditButton: false,
                hoverDeleteButton: false,
                hoverNewActionButton: false,
            }
        },        
    }
</script>