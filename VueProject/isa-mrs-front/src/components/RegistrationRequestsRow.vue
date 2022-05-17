<template>
    <tr :key="user.id" :class="{activeAcceptButton: hoverAcceptButton, activeDeclineButton: hoverDeclineButton}">
        <td class="align-middle"> {{ user.name }} {{ user.surname }} </td>
        <td class="align-middle"> {{ user.registrationReasoningDTO.registrationReason }} </td>
        <td class="align-middle" v-if="!isButtonHidden"> <button type="button" class="btn btn-success" @mouseover="hoverAcceptButton = true" @mouseleave="hoverAcceptButton = false" @click="acceptUser()">Accept registration</button> </td>
        <td class="align-middle" v-if="!isButtonHidden"> <button @mouseover="hoverDeclineButton = true" @mouseleave="hoverDeclineButton = false" type="button" class="btn btn-danger" @click="isButtonHidden = true"> Decline registration</button> </td>
        <td class="align-middle" v-if="isButtonHidden"> <textarea v-model="declineReasoning" placeholder="Reason for declining a registration: "></textarea></td>
        <td class="align-middle" v-if="isButtonHidden"> <button type="button" class="btn btn-danger" @click="declineUser()" @ :disabled="$v.declineReasoning.$invalid">Confirm declining a registration</button></td>
        <td class="align-middle" v-if="isButtonHidden"> <button type="button" class="btn btn-warning" @click="isButtonHidden = false"> Back </button></td>
    </tr>
</template>

<script>
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import { required, minLength } from 'vuelidate/lib/validators'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    Vue.use(VueAxios, axios)

    Vue.use(Vuelidate)

    export default {
        props: ["user"],
        data () {
            return {
                isButtonHidden: false,
                hoverAcceptButton: false,
                hoverDeclineButton: false,
                declineReasoning: ""
            }
        },
        methods: {
            declineUser(){
                axios.post("http://localhost:8088/users/decline",
                {
                    userId: this.user.id,
                    declineReasoning: this.declineReasoning
                },
                {
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    window.location.reload();
                })
            },
            acceptUser(){
                axios.post("http://localhost:8088/users/accept",
                {
                    userId: this.user.id
                },
                { 
                    headers:{
                        Authorization: 'Bearer ' + window.sessionStorage.getItem("accessToken")
                    }
                }).then(() => {
                    window.location.reload();
                })
            },
        },
        validations:{
            declineReasoning: {
                required,
                minLength: minLength(10)
            },
        }
    }
</script>