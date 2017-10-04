var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":86,"id":116,"methods":[{"el":46,"sc":5,"sl":44},{"el":49,"sc":5,"sl":47},{"el":52,"sc":5,"sl":50},{"el":59,"sc":5,"sl":57},{"el":62,"sc":5,"sl":60},{"el":65,"sc":5,"sl":63},{"el":84,"sc":5,"sl":70}],"name":"TestSuites","sl":35}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_11":{"methods":[{"sl":57},{"sl":60},{"sl":63}],"name":"testPojoGetterSetter","pass":true,"statements":[{"sl":58},{"sl":61},{"sl":64}]},"test_7":{"methods":[{"sl":44},{"sl":47},{"sl":50},{"sl":57},{"sl":60},{"sl":70}],"name":"testRunValidInputWithEmptyInputFolder","pass":true,"statements":[{"sl":45},{"sl":48},{"sl":51},{"sl":58},{"sl":61},{"sl":71},{"sl":72},{"sl":73},{"sl":74},{"sl":75},{"sl":76},{"sl":77},{"sl":78},{"sl":79},{"sl":80},{"sl":83}]},"test_8":{"methods":[{"sl":44},{"sl":47},{"sl":50},{"sl":57},{"sl":60},{"sl":63},{"sl":70}],"name":"testToXml","pass":true,"statements":[{"sl":45},{"sl":48},{"sl":51},{"sl":58},{"sl":61},{"sl":64},{"sl":71},{"sl":72},{"sl":73},{"sl":74},{"sl":75},{"sl":76},{"sl":77},{"sl":78},{"sl":79},{"sl":80},{"sl":81},{"sl":83}]},"test_9":{"methods":[{"sl":44},{"sl":47},{"sl":50},{"sl":57},{"sl":60},{"sl":63},{"sl":70}],"name":"testRunValidInputWithValidFolders","pass":true,"statements":[{"sl":45},{"sl":48},{"sl":51},{"sl":58},{"sl":61},{"sl":64},{"sl":71},{"sl":72},{"sl":73},{"sl":74},{"sl":75},{"sl":76},{"sl":77},{"sl":78},{"sl":79},{"sl":80},{"sl":81},{"sl":83}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [7, 8, 9], [7, 8, 9], [], [7, 8, 9], [7, 8, 9], [], [7, 8, 9], [7, 8, 9], [], [], [], [], [], [7, 8, 9, 11], [7, 8, 9, 11], [], [7, 8, 9, 11], [7, 8, 9, 11], [], [8, 9, 11], [8, 9, 11], [], [], [], [], [], [7, 8, 9], [7, 8, 9], [7, 8, 9], [7, 8, 9], [7, 8, 9], [7, 8, 9], [7, 8, 9], [7, 8, 9], [7, 8, 9], [7, 8, 9], [7, 8, 9], [8, 9], [], [7, 8, 9], [], [], []]
