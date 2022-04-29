<template>
    <tr :key="service.id" :class="{activeDeleteButton: hoverDeleteButton, activeEditButton: hoverEditButton}">
        <td class="align-middle text-center"> {{service.name}} </td>
        <td class="align-middle text-center"> <button type="button" @mouseover="hoverEditButton = true" @mouseleave="hoverEditButton = false" class="btn btn-warning" @click="editService">Edit service info</button></td>
        <td class="align-middle text-center"> <button type="button" @mouseover="hoverDeleteButton = true" @mouseleave="hoverDeleteButton = false" class="btn btn-danger" @click="deleteService">Delete service</button></td>
    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        props: ["service"],
        data(){
            return {
                hoverEditButton: false,
                hoverDeleteButton: false,
                mid: "",
                userService: ""
            }
        },        
        methods: {
            //router link na stranicu sa servisom koji ima dati ID
            editService(){
                
            },
            //poziv na back da izbrise servis
            deleteService(){
                this.determineUserService();
                let path = "http://localhost:8088/" + this.userService + "/deleteService"; 
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
            },
            determineUserService(){
                if (window.localStorage.getItem("role") === "ROLE_retreatOwner"){
                    this.userService = "retreats";
                }else if (window.localStorage.getItem("role") === "ROLE_shipOwner"){
                    this.userService = "ships";
                }else if (window.localStorage.getItem("role") === "ROLE_fishingInstructor"){
                    this.userService = "adventures";
                }               
            }
        },
    }
</script>