"use strict";

// Host:Port
const ADDR = "http://localhost:8080";

// Divs
const RESULTS_DIV = document.querySelector("#results-div");
const FORM_DIV = document.querySelector("#form-div");

// Buttons
const CREATE_BTN = document.querySelector("#create-btn");

// Inputs
const NAME = document.querySelector(".name-input");
const ARTIST = document.querySelector(".artist-input");
const YEAR = document.querySelector(".year-input");
const TYPE = document.querySelector(".type-input");

// Setup
const setup = () => {
    datepickerSetup();
    getAll();
}

// Get request
const getAll = () => {
    axios.get(`${ADDR}/getAll`)
        .then((resp) => {
            RESULTS_DIV.innerHTML = "";
            const RESULTS = resp.data;
            for (let result of RESULTS) {
                printResult(result);
            }
        }).catch((err) => console.error(err))
}

// Post request
const create = () => {
    // Validate the form
    if (!validateForm("create")) {
        return;
    }

    // Input values
    const NAME_VALUE = NAME.value;
    const ARTIST_VALUE = ARTIST.value;
    const YEAR_VALUE = YEAR.value;
    const TYPE_VALUE = TYPE.value;

    // New object to be created
    let obj = {
        "name": NAME_VALUE,
        "artist": ARTIST_VALUE,
        "year": YEAR_VALUE,
        "type": TYPE_VALUE
    };

    // Request
    axios.post(`${ADDR}/create`, obj)
        .then((resp) => {
            statusMsg(true);
            getAll();
        }).catch((err) => {
            console.error(err);
            statusMsg(false);
        })
}

// Put request
const update = () => {
    // Validate the form
    if (!validateForm("edit")) {
        return;
    }

    const EDIT_FORM = document.forms["editForm"];
  
    // New object for update
    let obj = {
        "name": EDIT_FORM["name"].value,
        "artist": EDIT_FORM["artist"].value,
        "year": EDIT_FORM["year"].value,
        "type": EDIT_FORM["type"].value
    };

    // Request
    axios.put(`${ADDR}/update/${EDIT_FORM["entry-id"].value}`, obj)
        .then((resp) => {
            // statusMsg(true);
            $("#edit-modal").modal("hide");
            getAll();
        }).catch((err) => {
            console.error(err);
            // statusMsg(false);
        })
}

// Delete request
const del = (id) => {
    axios.delete(`${ADDR}/remove/${id}`)
        .then((resp) => {
            getAll();
        }).catch((err) => {
            alert(err);
        })
}

const statusMsg = (bool) => {
    CREATE_BTN.setAttribute("disabled", "true");
    if (bool) {
        const ALERT_SUC = document.createElement("div");
        ALERT_SUC.setAttribute("class", "alert alert-success");
        ALERT_SUC.innerHTML = "Creation Successful"
        FORM_DIV.appendChild(ALERT_SUC);
        setTimeout(() => {
            FORM_DIV.removeChild(document.querySelector(".alert-success"));
            CREATE_BTN.removeAttribute("disabled");
        }, 2000)
    } else {
        const ALERT_ERR = document.createElement("div");
        ALERT_ERR.setAttribute("class", "alert alert-danger");
        ALERT_ERR.innerHTML = "Creation Failed";
        FORM_DIV.appendChild(ALERT_ERR);
        setTimeout(() => {
            FORM_DIV.removeChild(document.querySelector(".alert-danger"));
            CREATE_BTN.removeAttribute("disabled");
        }, 2000);
    }
}

const printResult = (result) => {
    const ENTRY_DIV = document.createElement("div");
    ENTRY_DIV.setAttribute("class", "entry-div");

    const ENTRY = document.createElement("div");
    ENTRY.setAttribute("class", "entry");
    ENTRY.textContent = `Name: ${result.name} Artist: ${result.artist} Year: ${result.year} Type: ${result.type}`;

    const EDIT = document.createElement("button");
    EDIT.type = "button";
    EDIT.textContent = "Edit";
    EDIT.id = `${result.id}`;
    EDIT.setAttribute("class", "btn btn-sm btn-primary edit-btn");
    EDIT.setAttribute("onClick", "openEdit(this.id)");

    const DEL = document.createElement("button");
    DEL.type = "button";
    DEL.textContent = "Delete";
    DEL.id = `${result.id}`;
    DEL.setAttribute("class", "btn btn-sm btn-danger del-btn");
    DEL.setAttribute("onClick", "del(this.id)");

    ENTRY_DIV.appendChild(ENTRY);
    ENTRY_DIV.appendChild(EDIT);
    ENTRY_DIV.appendChild(DEL);
    
    RESULTS_DIV.appendChild(ENTRY_DIV);
}

const validateForm = (type) => {
    const CREATE_FORM = document.forms[`${type}Form`];
    if (CREATE_FORM["name"].value == "" || CREATE_FORM["artist"].value == "" || CREATE_FORM["year"].value == "" || CREATE_FORM["type"].value == "") {
        alert("All fields require a value!");
        return false;
    }
    return true;
}

const openEdit = (id) => {
    // Show modal and configure date field
    $("#edit-modal").modal("show");
    datepickerSetup();

    // Get the current values for selected entry
    axios.get(`${ADDR}/getOne/${id}`)
        .then((resp) => {
            const ENTRY = resp.data;
            console.log(ENTRY);
            // Populate modal form with current values
            const EDIT_FORM = document.forms["editForm"];
            EDIT_FORM["name"].value = ENTRY.name;
            EDIT_FORM["artist"].value = ENTRY.artist;
            EDIT_FORM["year"].value = ENTRY.year;
            EDIT_FORM["type"].value = ENTRY.type;
            EDIT_FORM["entry-id"].value = ENTRY.id;
        }).catch((err) => console.error(err))
}

const datepickerSetup = () => {
    // Sets to year only
    $(".year-input").datepicker({
        format: "yyyy",
        viewMode: "years",
        minViewMode: "years"
    });
}