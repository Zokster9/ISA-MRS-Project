Vue.use(vuelidate.default)
Vue.component("add-adventure", {
    template:`
    <form class="w-50 mx-auto mt-5">
        <h2> New adventure </h2>
        <div class="form-group mb-3">
            <label for="adventureName">Adventure name:</label>
            <input v-model="form.adventureName" type="text" class="form-control" placeholder="This field is required!">
        </div>
        <div class="form-group mb-3">
            <label for="descriptionNameId">Description: </label>
            <textarea v-model="form.description" class="form-control" id="descriptionNameId" placeholder="This field is required!"></textarea>
        </div>
        <div class="form-group mb-3">
           <label for="rulesOfConduct">Rules of conduct: </label>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.rulesOfConduct" value="Mobile phone must be turned off">
                <label>Mobile phone must be turned off</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.rulesOfConduct" value="Must be properly dressed">
                <label>Must be properly dressed</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.rulesOfConduct" value="Bring your own fishing equipment">
                <label>Bring your own fishing equipment</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.rulesOfConduct" value="Can't be noisy">
                <label>Can't be noisy</label>
           </div>
        </div>
        <div class="form-group mb-3">
            <label for="price">Price: </label>
            <input v-model="form.price" class="form-control" id="priceId" placeholder="This field is required!">
            <small id="priceHelp" class="form-text text-muted"> Price has to be at least 5 dollars.</small>
        </div>
        <div class="form-group mb-3">
            <label for="maxNumOfPeople">Maximum number of people: </label>
            <input v-model="form.maxNumOfPeople" class="form-control" id="maxNumOfPeopleId" placeholder="This field is required!">
            <small id="maxHelp" class="form-text text-muted"> Maximum number of people should at least be 1.</small>
        </div>
        <div class="form-group mb-3">
            <label for="fishingEquipment">Fishing equipment: </label>
            <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="hooks">
                <label>Hooks</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="lines">
                <label>Lines</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="floats">
                <label>Floats</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="rods">
                <label>Rods</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="reels">
                <label>Reels</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="baits">
                <label>Baits</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="lures">
                <label>Lures</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="spears">
                <label>Spears</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="nets">
                <label>Nets</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="gaffs">
                <label>Gaffs</label>
           </div>
           <div class="form-group mb-3">
                <input type="checkbox" v-model="form.fishingEquipment" value="traps">
                <label>Traps</label>
           </div>
        </div>
        <div class="form-group mb-3">
            <label for="reservationCancellationConditions">Reservation cancellation conditions: </label>
            <textarea v-model="form.reservationCancellationConditions" class="form-control" id="fishingEquipmentId" placeholder="This field is required!"></textarea>
        </div>
        <div class="form-group mb-3">
            <label for="instructorBiography">Instructor biography: </label>
            <textarea v-model="form.instructorBiography" class="form-control" id="instructorBiographyId" placeholder="This field is required!"></textarea>
        </div>
        <label>Pictures:</label>
        <div class="form-group mb-3">
            <input accept="image/*" type="file" class="form-control" @change="addPicture" multiple/>
        </div>
        <div class="form-group- mb-3">
            <button @click="sendData" type="submit" @ :disabled="$v.form.$invalid" class="btn btn-primary float-end" >Add adventure</button>
        </div>
    </form>
    `,
    data(){
        return {
            form:{
                adventureName: "",
                description: "",
                rulesOfConduct: [],
                price: 5,
                maxNumOfPeople: 1,
                fishingEquipment: [],
                reservationCancellationConditions: "",
                instructorBiography: "",
                pictures: []
            }
        }
    },
    methods: {
        addPicture(e) {
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            for (file of files){
                this.form.pictures.push(file.name)
            }
        },
        sendData(){
            axios.post("/adventures", {
                name: this.form.adventureName,
                description: this.form.description,
                rulesOfConduct: this.form.rulesOfConduct,
                price: this.form.price,
                maxNumOfPeople: this.form.maxNumOfPeople,
                fishingEquipment: this.form.fishingEquipment,
                reservationCancellationConditions: this.form.reservationCancellationConditions,
                instructorBiography: this.form.instructorBiography,
                pictures: this.form.pictures
                //zatim header jwt token
            }).then(function(response){
                console.log("uspeo sam");
                console.log(response);
            }).catch(function(error){
               alert("ne valja brt");
            });
        }
    },
    validations:{
        form:{
            adventureName: {
                required: validators.required,
                minLength: validators.minLength(5)
            },
            description: {
                required: validators.required,
                minLength: validators.minLength(5)
            },
            price: {
                required: validators.required,
                minValueValue: validators.minValue(5)
            },
            maxNumOfPeople: {
                required: validators.required,
                minValueValue: validators.minValue(1)
            },
            reservationCancellationConditions: {
                required: validators.required,
                minLength: validators.minLength(5)
            },
            instructorBiography: {
                required: validators.required,
                minLength: validators.minLength(5)
            }
        }
    }

})