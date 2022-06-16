<template>
    <tr :key="service.id" :class="{activeDeleteButton: hoverDeleteButton, activeViewButton: hoverViewButton}">
        <td class="align-middle text-center">
            <figcaption class="mb-1"> {{service.name}} </figcaption> 
            <img :src="require('@/assets/' + service.pictures[0])" style="width:200px; height:200px" class="mb-1">
            <figcaption class="mb-1"> <button type="button" class="btn btn-primary" style="width:130px" @mouseover="hoverViewButton = true"
            @mouseleave="hoverViewButton = false" @click="redirect(service.id)"> View service </button> </figcaption>
            <figcaption class="mb-1"> <button type="button" class="btn btn-danger" style="width:130px"  @mouseover="hoverDeleteButton = true" 
            @mouseleave="hoverDeleteButton = false" @click="deleteService(service.id)"> Delete Service </button> </figcaption>
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
        props: ["service"],
        methods: {
            redirect(id){
                axios.get("http://localhost:8088/users/findOwnerOfService/" + id,
                {
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then((response)=>{
                    if (response.data.privilegedUser === "FISHING_INSTRUCTOR") {
                        router.push('/adventure/' + id);
                    } else if (response.data.privilegedUser === "RETREAT_OWNER") {
                        router.push('/retreat/' + id);
                    } else if (response.data.privilegedUser === "SHIP_OWNER") {
                        router.push('/ship/' + id);
                    } 
                })                
            },
            deleteService(id){
                axios.delete("http://localhost:8088/services/delete/" + id,
                {
                    headers:{
                        Authorization: "Bearer " + window.sessionStorage.getItem("accessToken")
                    }
                }).then(()=>{
                    alert("Service has been successfully deleted!")
                    window.location.reload()
                }).catch((error) => {
                    if (error.response.status === 409) alert("Error. Pending reservation for service exists.");
                    else alert("Error occured. You can not delete this adventure.");
                })
            }
        },
        data(){
            return {
                hoverDeleteButton: false,
                hoverViewButton: false,
            }
        },  
    }
</script>