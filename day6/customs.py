f = open("day6/input.txt", "r")
contents = f.read()
f.close()

groups = [line.splitlines() for line in contents.split("\n\n")]
total = sum([len(set(''.join(group))) for group in groups])

print(total)