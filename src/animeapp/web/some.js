document.addEventListener("DOMContentLoaded", () => {
    const apiUrl = "http://localhost:3000/anime";
    const animeList = document.getElementById("anime-list");

    fetch(apiUrl)
	.then((response) => response.json())
	.then((data) => {
	    data.forEach((anime) => {
		const listItem = document.createElement("li");
		listItem.textContent = anime["anime/title"];
		animeList.appendChild(listItem);
	    });
	})
	.catch((error) => console.error("Error fetching data: ", error));
});
