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
            @mouseleave="hoverAcceptButton = false" @click="sendResponse(complaint.id, complaint.clientName, complaint.clientSurname, complaint.privilegedUserName,
             complaint.privilegedUserSurname, complaint.serviceName, complaint.servicePictures, complaint.complaint)" 
            @ :disabled="$v.response.$invalid">Send response</button>
        </td>

    </tr>
</template>

<script>
    import Vue from 'vue' 
    import axios from 'axios'
    import VueAxios from 'vue-axios'
    import Vuelidate from 'vuelidate'
    //import router from '@/router'
    import { required, minLength } from 'vuelidate/lib/validators'

    Vue.use(VueAxios, axios)
    Vue.use(Vuelidate)

    export default {
        props: ["complaint"],
        methods: {
            sendResponse(id, clientName, clientSurname, privilegedUserName, privilegedUserSurname, serviceName, servicePictures, complaint){
                axios.post("http://localhost:8088/complaints/response", {
                    id: id,
                    complaint: complaint,
                    response: this.response,
                    isAnswered: true,
                    clientName: clientName,
                    clientSurname: clientSurname,
                    privilegedUserName: privilegedUserName,
                    privilegedUserSurname: privilegedUserSurname,
                    serviceName: serviceName,
                    servicePictures: servicePictures,
                },{
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    window.location.reload();
                })
            }
        },
        data(){
            return {
                hoverAcceptButton: false,
                response: ""
            }
        },
        validations:{
            response: {
                required,
                minLength: minLength(10)
            },
        }
    }
</script>