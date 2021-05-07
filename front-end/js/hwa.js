"use strict";

const resultsDiv = document.querySelector("#results-div");

axios
    .get("https://reqres.in/api/users")
    .then((response) => {
        printResults(response.data.data);
    })
    .catch((err) => {
        console.err(err);
    })

console.log("hello");

const printResults = (results) => {
    for (let result of results) {
        const p = document.createElement("p");
        const text = document.createTextNode(`First name: ${result.first_name} Last name: ${result.last_name} Email: ${result.email}`);
        p.appendChild(text);
        resultsDiv.appendChild(p);
    }
}