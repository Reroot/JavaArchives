const input = [
	"Simon 4",
	"Anna 10",
	"Simon 4",
	"Anna 9",
	"Anna 5",
	"Edward 10"
];

// Transform the data to make it more manageable.
const data = Object.entries(
	input
		.map((s) => s.split(" "))
		.map(([name, score]) => ({
			name: name,
			score: score
		}))
		.reduce((a, c) => {
			if (a.hasOwnProperty(c.name)) {
				a[c.name].scores.push(Number.parseInt(c.score));
			} else {
				a[c.name] = {
					scores: [Number.parseInt(c.score)]
				};
			}
			return a;
		}, {})
).map(([k, v]) => {
	return {
		name: k,
		count: v.scores.length,
		average: v.scores.reduce((a, c) => a + c) / v.scores.length
	};
});

const alphaSortComparator = (a, b) => {
	// Use toUpperCase() to ignore character casing
	const A = a.name;
	const B = b.name;

	let comparison = 0;
	if (A > B) {
		comparison = 1;
	} else if (A < B) {
		comparison = -1;
	}
	return comparison;
};

const meritSortComparator = (a, b) => {
	const A = a.average;
	const B = b.average;

	let comparison = 0;
	if (A < B) {
		comparison = 1;
	} else if (A > B) {
		comparison = -1;
	}
	return comparison;
};

const displayAlphaOrder = (data = []) => {
	const alphaSortedData = data.concat().sort(alphaSortComparator);
	console.log("Alpha order");
	for (const item of alphaSortedData) {
		console.log(`${item.name} ${item.count} ${item.average.toFixed(1)}`);
	}
};

const displayMeritOrder = (data = []) => {
	const meritSortedData = data.concat().sort(meritSortComparator);
	console.log("\nMerit order");
	for (const [i, item] of meritSortedData.entries()) {
		console.log(
			`${i + 1} ${item.name} ${item.count} ${item.average.toFixed(1)}`
		);
	}
};

console.log(`
========================================
|                 DATA                 |
========================================`);
console.log(data);
console.log();
displayAlphaOrder(data);
displayMeritOrder(data);
