<template>
    <tr :key="service.id" :class="{activeDeleteButton: hoverDeleteButton}">
        <td class="align-middle text-center"><figcaption class="mb-1"> {{service.name}} </figcaption> 
        <router-link exact to="/"><img :src="require('@/assets/' + service.pictures[0])" style="width:200px; height:200px"> 
        <figcaption><button type="button" @mouseover="hoverDeleteButton = true" @mouseleave="hoverDeleteButton = false" class="btn btn-danger mt-1" 
        @click="deleteService(service.id)">Delete service</button></figcaption> </router-link></td>
    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    export default {
        props: ["service"],
        methods: {

        },

        data(){
            return {
                hoverDeleteButton: false,
                userService: ""
                }
            },        
        }
</script>