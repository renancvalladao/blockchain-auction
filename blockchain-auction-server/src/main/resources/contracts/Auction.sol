// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.8.0;

contract Auction {

    struct OptionalRequirement {
        uint8 value;
        bool required;
    }

    struct Requirements {
        string vnfName;
        string vnfType;
        uint8 numCpus;
        uint8 memSize;
        OptionalRequirement maxDelay;
        OptionalRequirement bandwidth;
    }

    struct WinnerInfo {
        address bidderAddress;
        uint cost;
    }

    struct Bid {
        address bidder;
        uint value;
    }

    Requirements requirements;
    address owner;
    address lowestBidder;
    uint lowestBid;
    uint endTime;
    bool ended;
    Bid[] bidHistory;

    constructor(Requirements memory _requirements, uint biddingTime) {
        requirements = _requirements;
        owner = msg.sender;
        endTime = block.timestamp + biddingTime;
    }

    function getRequirements() public view returns (Requirements memory) {
        return requirements;
    }

    function getWinner() public view returns (WinnerInfo memory) {
        require(ended, "Auction has not ended yet");
        return WinnerInfo(lowestBidder, lowestBid);
    }

    function getBidHistory() public view returns (Bid[] memory) {
        return bidHistory;
    }

    function placeBid(uint value) external {
        require(!ended, "Auction has already ended");
        require(value > 0, "Bid value must be greater than zero");
        if (value < lowestBid || lowestBid == 0) {
            lowestBid = value;
            lowestBidder = msg.sender;
        }
        bidHistory.push(Bid(msg.sender, value));
    }

    function finishAuction() external {
        require(msg.sender == owner, "Only the owner can finish the auction");
        require(block.timestamp >= endTime, "Auction has not ended yet");
        ended = true;
    }

}
