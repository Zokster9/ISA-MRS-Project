Vue.use(vuelidate.default)

Vue.component("termination-requests-row", {
    props: ["termination"],
    template:`
        <tr :key="termination.userId" :class="{activeAcceptButton: hoverAcceptButton, activeDeclineButton: hoverDeclineButton}">
            <td class="align-middle"> {{ termination.name }} {{ termination.surname }} </td>
            <td class="align-middle"> {{ termination.terminationReasoning }} </td>
            
            <td class="align-middle" v-if="!isAcceptButtonHidden && !hideAcceptButton"> <button type="button" class="btn btn-success" @mouseover="hoverAcceptButton = true" @mouseleave="hoverAcceptButton = false" @click="acceptButtonPressed()">Accept account termination</button> </td>
            <td class="align-middle" v-if="isAcceptButtonHidden"> <textarea v-model="acceptReasoning" placeholder="Reason for accepting termination of an account: "></textarea></td>
            <td class="align-middle" v-if="isAcceptButtonHidden"> <button type="button" class="btn btn-success" @click="acceptTermination(termination.userId)" @ :disabled="$v.acceptReasoning.$invalid">Confirm accepting account termination</button></td>
            <td class="align-middle" v-if="isAcceptButtonHidden"> <button type="button" class="btn btn-warning" @click="acceptBackPressed()"> Back </button></td>
            
            <td class="align-middle" v-if="!isDeclineButtonHidden && !hideDeclineButton"> <button @mouseover="hoverDeclineButton = true" @mouseleave="hoverDeclineButton = false" type="button" class="btn btn-danger" @click="declineButtonPressed()"> Decline account termination</button> </td>
            <td class="align-middle" v-if="isDeclineButtonHidden"> <textarea v-model="declineReasoning" placeholder="Reason for declining termination of an account: "></textarea></td>
            <td class="align-middle" v-if="isDeclineButtonHidden"> <button type="button" class="btn btn-danger" @click="declineTermination(termination.userId)" @ :disabled="$v.declineReasoning.$invalid">Confirm declining a registration</button></td>
            <td class="align-middle" v-if="isDeclineButtonHidden"> <button type="button" class="btn btn-warning" @click="declineBackPressed()"> Back </button></td>
        </tr>
    `,
    data(){
        return {
            isAcceptButtonHidden: false,
            isDeclineButtonHidden: false,
            hoverAcceptButton: false,
            hoverDeclineButton: false,
            hideAcceptButton: false,
            hideDeclineButton: false,
            acceptReasoning: "",
            declineReasoning: ""
        }
    },
    methods: {
        acceptButtonPressed(){
            this.isAcceptButtonHidden = true;
            this.hideDeclineButton = true;
        },
        declineButtonPressed(){
            this.isDeclineButtonHidden = true;
            this.hideAcceptButton = true;
        },
        acceptBackPressed(){
            this.hideDeclineButton = false;
            this.isAcceptButtonHidden = false;
        },
        declineBackPressed(){
            this.hideAcceptButton = false;
            this.isDeclineButtonHidden = false;
        },
        declineTermination(id){
            axios.post("/users/decline?id=" + id + "&declineReasoning=" + this.declineReasoning).then((response) => {
                window.location.reload();
            })
        },
        acceptTermination(id){
            axios.post("/users/accept/" + id).then((response) => {
                window.location.reload();
            })
        },
    },
    validations:{
        acceptReasoning: {
            required: validators.required,
            minLength: validators.minLength(10)
        },
        declineReasoning: {
            required: validators.required,
            minLength: validators.minLength(10)
        },
    }
})