Vue.use(vuelidate.default)

Vue.component("registration-requests-row", {
    props: ["user"],
    template:`
        <tr :key="user.id" :class="{activeAcceptButton: hoverAcceptButton, activeDeclineButton: hoverDeclineButton}">
            <td class="align-middle"> {{ user.name }} {{ user.surname }} </td>
            <td class="align-middle"> {{ user.registrationReasoningDTO.registrationReason }} </td>
            <td class="align-middle"> <button type="button" class="btn btn-success" @mouseover="hoverAcceptButton = true" @mouseleave="hoverAcceptButton = false" @click="acceptUser(user.id)">Accept registration</button> </td>
            <td class="align-middle" v-if="!isButtonHidden"> <button @mouseover="hoverDeclineButton = true" @mouseleave="hoverDeclineButton = false" type="button" class="btn btn-danger" @click="isButtonHidden = true"> Decline registration</button> </td>
            <td class="align-middle" v-if="isButtonHidden"> <textarea v-model="declineReasoning" placeholder="Reason for declining a registration: "></textarea></td>
            <td class="align-middle" v-if="isButtonHidden"> <button type="button" class="btn btn-danger" @click="declineUser(user.id)" @ :disabled="$v.declineReasoning.$invalid">Confirm declining a registration</button></td>
        </tr>
    `,
    data(){
        return {
            isButtonHidden: false,
            hoverAcceptButton: false,
            hoverDeclineButton: false,
            declineReasoning: ""
        }
    },
    methods: {
        declineUser(id){
            axios.post("/users/decline?id=" + id + "&declineReasoning=" + this.declineReasoning).then((response) => {
                alert("Uspeh");
                window.location.reload();
            })
        },
        acceptUser(id){
            axios.post("/users/accept/" + id).then((response) => {
                window.location.reload();
            })
        },
    },
    validations:{
        declineReasoning: {
            required: validators.required,
            minLength: validators.minLength(10)
        },
    }
})