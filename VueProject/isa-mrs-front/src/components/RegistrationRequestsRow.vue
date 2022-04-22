<template>
    <tr :key="user.id" :class="{activeAcceptButton: hoverAcceptButton, activeDeclineButton: hoverDeclineButton}">
        <td class="align-middle"> {{ user.name }} {{ user.surname }} </td>
        <td class="align-middle"> {{ user.registrationReasoningDTO.registrationReason }} </td>
        <td class="align-middle" v-if="!isButtonHidden"> <button type="button" class="btn btn-success" @mouseover="hoverAcceptButton = true" @mouseleave="hoverAcceptButton = false" @click="acceptUser(user.id)">Accept registration</button> </td>
        <td class="align-middle" v-if="!isButtonHidden"> <button @mouseover="hoverDeclineButton = true" @mouseleave="hoverDeclineButton = false" type="button" class="btn btn-danger" @click="isButtonHidden = true"> Decline registration</button> </td>
        <td class="align-middle" v-if="isButtonHidden"> <textarea v-model="declineReasoning" placeholder="Reason for declining a registration: "></textarea></td>
        <td class="align-middle" v-if="isButtonHidden"> <button type="button" class="btn btn-danger" @click="declineUser(user.id)" @ :disabled="$v.declineReasoning.$invalid">Confirm declining a registration</button></td>
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
            declineUser(id){
                axios.post("http://localhost:8088/users/decline?id=" + id + "&declineReasoning=" + this.declineReasoning).then(() => {
                    window.location.reload();
                })
            },
            acceptUser(id){
                axios.post("http://localhost:8088/users/accept/" + id).then(() => {
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