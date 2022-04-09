Vue.component('add-ship', {
    template: `
        <form class="w-50 mx-auto mt-5">
            <h2> New ship </h2>
                <div class="form-group mb-3">
                    <label>Name</label>
                    <input v-model="form.name" type="text" class="form-control"/>
                    <p v-if="!nameIsValid" class="alert alert-danger">Name is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Description</label>
                    <textarea v-model="form.description" type="text" class="form-control"/>
                    <p v-if="!descriptionIsValid" class="alert alert-danger">Description is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Country</label>
                    <input v-model="form.country" type="text" class="form-control"/>
                    <p v-if="!countryIsValid" class="alert alert-danger">Country is required, and it starts with first capital letter.</p>
                </div>
                <div class="form-group mb-3">
                    <label>City</label>
                    <input v-model="form.city" type="text" class="form-control"/>
                    <p v-if="!cityIsValid" class="alert alert-danger">City is required, and it starts with first capital letter.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Address</label>
                    <input v-model="form.address" type="text" class="form-control"/>
                    <p v-if="!addressIsValid" class="alert alert-danger">Address is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Ship type</label>
                    <input v-model="form.shipType" type="text" class="form-control"/>
                    <p v-if="!typeIsValid" class="alert alert-danger">Ship type is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Ship length</label>
                    <input v-model.number="form.shipLength" type="number" class="form-control"/>
                    <p v-if="!lengthIsValid" class="alert alert-danger">Ship length is required, and it must be number greater than 5.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Engine number</label>
                    <input v-model="form.engineNumber" type="text" class="form-control"/>
                    <p v-if="!engineNumberIsValid" class="alert alert-danger">Engine number is required.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Engine power</label>
                    <input v-model.number="form.enginePower" type="number" class="form-control"/>
                    <p v-if="!enginePowerIsValid" class="alert alert-danger">Engine power is required, and it must be number greater than 40.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Maximal speed</label>
                    <input v-model="form.maxSpeed" type="text" class="form-control"/>
                    <p v-if="!maxSpeedIsValid" class="alert alert-danger">Maximal speed is required, and it must be in format: number km/h.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Capacity</label>
                    <input v-model.number="form.capacity" type="number" class="form-control"/>
                    <p v-if="!capacityIsValid" class="alert alert-danger">Capacity is required, and it must be number greater than 0.</p>
                </div>
                <div class="form-group mb-3">
                    <label>Rules of conduction:</label>
                    <br>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Smoking disallowed"/>
                        <label>Smoking disallowed</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Pet friendly"/>
                        <label>Pet friendly</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Parties disallowed"/>
                        <label>Parties disallowed</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Drinking disallowed"/>
                        <label>Drinking disallowed</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.rulesOfConduction" value="Weapons disallowed"/>
                        <label>Weapons disallowed</label>
                    </div>
                </div>
                <div class="form-group mb-3">
                    <label>Navigation equipment:</label>
                    <br>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.navigationEquipment" value="Gyro Compass"/>
                        <label>Gyro Compass</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.navigationEquipment" value="Radar"/>
                        <label>Radar</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.navigationEquipment" value="Autopilot"/>
                        <label>Autopilot</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.navigationEquipment" value="Echo sounder"/>
                        <label>Echo sounder</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.navigationEquipment" value="Magnetic Compass"/>
                        <label>Magnetic Compass</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.navigationEquipment" value="ARPA"/>
                        <label>ARPA</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.navigationEquipment" value="GPS Receiver"/>
                        <label>GPS Receiver</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.navigationEquipment" value="Navigation lamps"/>
                        <label>Navigation lamps</label>
                    </div>
                </div>
                <div class="form-group mb-3">
                    <label>Fishing equipment: </label>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Hooks">
                        <label>Hooks</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Lines">
                        <label>Lines</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Floats">
                        <label>Floats</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Rods">
                        <label>Rods</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Reels">
                        <label>Reels</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Baits">
                        <label>Baits</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Lures">
                        <label>Lures</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Spears">
                        <label>Spears</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Nets">
                        <label>Nets</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Gaffs">
                        <label>Gaffs</label>
                    </div>
                    <div class="form-group mb-3">
                        <input type="checkbox" v-model="form.fishingEquipment" value="Traps">
                        <label>Traps</label>
                    </div>
                </div>
                <div class="form-group mb-3">
                    <label>Pictures:</label>
                    <input accept="image/*" type="file" class="form-control" @change="addPicture($event)" multiple/>
                </div>
                <div class="form-group mb-3">
                    <label>Reservation cancellation conditions</label>
                    <textarea v-model="form.reservationCancellationConditions" type="text" class="form-control"/>
                    <p v-if="!cancellationIsValid" class="alert alert-danger">Reservation cancellation conditions are required.</p>
                </div>
                <div class="form-group mb-3">
                    <button @click="addRetreat" type="submit" class="btn btn-primary float-end">Add retreat</button>
                </div>
        </form>
    `,

    data() {
        return {
            form: {
                name: null,
                description: null,
                country: null,
                city: null,
                address: null,
                shipType: null,
                shipLength: null,
                engineNumber: null,
                enginePower: null,
                maxSpeed: null,
                capacity: null,
                rulesOfConduction: [],
                navigationEquipment: [],
                fishingEquipment: [],
                reservationCancellationConditions: null,
                pictures: []
            }
        }
    },

    computed: {
        nameIsValid() {
            return !!this.form.name;
        },
        descriptionIsValid() {
            return !!this.form.description;
        },
        countryIsValid() {
            return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.country) && !!this.form.country;
        },
        cityIsValid() {
            return RegExp(/([A-Z]{1})([a-z]+)([^0-9]*)$/).test(this.form.city) && !!this.form.city;
        },
        addressIsValid() {
            return !!this.form.address;
        },
        typeIsValid() {
            return !!this.form.shipType;
        },
        lengthIsValid() {
            return typeof this.form.shipLength === 'number' && !!this.form.shipLength && this.form.shipLength > 5;
        },
        engineNumberIsValid() {
            return !!this.form.engineNumber;
        },
        enginePowerIsValid() {
            return typeof this.form.enginePower === 'number' && !!this.form.enginePower && this.form.enginePower > 40;
        },
        maxSpeedIsValid() {
            return RegExp(/[1-9][0-9](.*)km(\/|\\)(h)/).test(this.form.maxSpeed) && !!this.form.city;
        },
        capacityIsValid() {
            return  typeof this.form.capacity === 'number' && !!this.form.capacity && this.form.capacity > 0;
        },
        cancellationIsValid() {
            return !!this.form.reservationCancellationConditions;
        },
        formIsValid() {
            return this.nameIsValid && this.descriptionIsValid && this.countryIsValid && this.cityIsValid && this.addressIsValid
                    && this.typeIsValid && this.lengthIsValid && this.engineNumberIsValid && this.enginePowerIsValid
                    && this.maxSpeedIsValid && this.capacityIsValid && this.cancellationIsValid;
        }
    },

    methods: {

        addRetreat() {
            if (this.formIsValid) {
                console.log("Dobri su parametri za brod.");
            }
        },

        addPicture(e) {
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            for (let file of files) this.form.pictures.push(file.name);
        }
    }
});