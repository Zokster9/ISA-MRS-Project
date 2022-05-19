<template>
    <tr :key="complaint.id" :class="{activeAcceptButton: hoverAcceptButton}">
        <td class="align-middle text-center"> {{ complaint.clientName }} {{complaint.clientSurname}}</td>
        <td class="align-middle text-center"> {{ complaint.privilegedUserName }} {{complaint.privilegedUserSurname}}</td>
        <td class="align-middle text-center">
            <figcaption class="mb-1"> {{complaint.serviceName}} </figcaption> 
            <img :src="require('@/assets/' + complaint.servicePictures[0])" style="width:200px; height:200px" class="mb-1"> 
        </td>
        <td class="align-middle text-center w-25">  {{complaint.complaint}} </td>
        <td class="align-middle text-center">  
            <textarea v-model="response" placeholder="Response: "> </textarea> 
        </td>
        <td class="align-middle text-center">
            <button type="button" class="btn btn-success" @mouseover="hoverAcceptButton = true" 
            @mouseleave="hoverAcceptButton = false" @click="sendResponse()">Send response</button>
        </td>

    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    //import router from '@/router'

    Vue.use(VueAxios, axios)

    export default {
        props: ["complaint"],
        methods: {
            sendResponse(){

            }
        },
        data(){
            return {
                hoverAcceptButton: false,
                response: ""
            }
        },  
    }
</script>